import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

/**
 * Parser class responsible for parsing user input and creating Task objects.
 * This class handles the conversion of string commands into structured Task objects,
 * including parsing dates and times in various formats.
 */
public class Parser {
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_TIME_SEPARATOR = " /from | /to ";

    /**
     * Parses a user input string into command components.
     * Splits the input into a command type and its details.
     *
     * @param userInput         - The raw input string from the user
     * @return                  - String array where index 0 contains the command type and index 1 contains the details
     * @throws PlopBotException - If the input is null, empty, or cannot be parsed
     */
    public String[] parseCommand(String userInput) throws PlopBotException {
        if (userInput == null || userInput.trim().isEmpty()) {
            throw new PlopBotException("Empty command");
        }
        return userInput.split(" ", 2);
    }

    /**
     * Creates a Task object based on the parsed command components.
     * Supports creation of todo, deadline, and event tasks.
     *
     * @param commandParts      - Array containing the command type and details
     * @return                  - A new Task object of the appropriate type
     * @throws PlopBotException - If the command format is invalid or task creation fails
     */
    public Task parseTask(String[] commandParts) throws PlopBotException {
        validateCommandParts(commandParts);
        String type = commandParts[0];
        String details = commandParts[1];

        return switch (type) {
            case "todo" -> createTodoTask(details);
            case "deadline" -> createDeadlineTask(details);
            case "event" -> createEventTask(details);
            default -> throw new PlopBotException("Unknown task type: " + type);
        };
    }

    /**
     * Validates that the command parts array contains the required components.
     *
     * @param commandParts      - Array of command components to validate
     * @throws PlopBotException - If the array is null or has insufficient components
     */
    private void validateCommandParts(String[] commandParts) throws PlopBotException {
        if (commandParts == null || commandParts.length < 2) {
            throw new PlopBotException("Invalid task format");
        }
    }

    /**
     * Creates a simple todo task with the given description.
     *
     * @param details - The description of the todo task
     * @return        - A new Task object representing a todo task
     */
    private Task createTodoTask(String details) {
        return new Task(details);
    }

    /**
     * Creates a deadline task with the given description and deadline date.
     *
     * @param details           - The description and deadline information
     * @return                  - A new Task object representing a deadline task
     * @throws PlopBotException - If the deadline format is invalid
     */
    private Task createDeadlineTask(String details) throws PlopBotException {
        try {
            String[] deadlineParts = details.split(DEADLINE_SEPARATOR, 2);
            if (deadlineParts.length != 2) {
                throw new PlopBotException("Invalid deadline format. Usage: deadline description /by DATE");
            }
            return new Task(deadlineParts[0], parseDateString(deadlineParts[1]));
        } catch (IllegalArgumentException e) {
            throw new PlopBotException("Invalid date format: " + e.getMessage());
        }
    }

    /**
     * Creates an event task with the given description, start time, and end time.
     *
     * @param details           - The description and event timing information
     * @return                  - A new Task object representing an event task
     * @throws PlopBotException - If the event format or timing is invalid
     */
    private Task createEventTask(String details) throws PlopBotException {
        try {
            String[] eventParts = details.split(EVENT_TIME_SEPARATOR);
            if (eventParts.length != 3) {
                throw new PlopBotException("Invalid event format. Usage: event description /from START_TIME /to END_TIME");
            }

            validateEventTimeParts(eventParts);
            return createEventTaskFromParts(eventParts);
        } catch (IllegalArgumentException e) {
            throw new PlopBotException("Invalid date/time format: " + e.getMessage());
        }
    }

    /**
     * Validates that event time parts are not empty.
     *
     * @param eventParts        - Array containing event description and time components
     * @throws PlopBotException - If either time component is empty
     */
    private void validateEventTimeParts(String[] eventParts) throws PlopBotException {
        if (eventParts[1].trim().isEmpty()) {
            throw new PlopBotException("Start time cannot be empty. Usage: event description /from START_TIME /to END_TIME");
        }
        if (eventParts[2].trim().isEmpty()) {
            throw new PlopBotException("End time cannot be empty. Usage: event description /from START_TIME /to END_TIME");
        }
    }

    /**
     * Creates an event task from validated event parts.
     *
     * @param eventParts        - Array containing event description and time components
     * @return                  - A new Task object representing an event task
     * @throws PlopBotException - If the time parsing fails or end time is before start time
     */
    private Task createEventTaskFromParts(String[] eventParts) throws PlopBotException {
        try {
            LocalDateTime startTime = parseDateTimeString(eventParts[1], null);
            LocalDateTime endTime = parseDateTimeString(eventParts[2], startTime);
            validateEventTimes(startTime, endTime);
            return new Task(eventParts[0], startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new PlopBotException("Invalid date/time format. Time must be in format 'YYYY-MM-DD HH:mm' or 'DAY HHam/pm'");
        }
    }

    /**
     * Validates that the end time is not before the start time.
     *
     * @param start             - Event start time
     * @param end               - Event end time
     * @throws PlopBotException - If end time is before start time
     */
    private void validateEventTimes(LocalDateTime start, LocalDateTime end) throws PlopBotException {
        if (end.isBefore(start)) {
            throw new PlopBotException("End time cannot be before start time");
        }
    }

    /**
     * Parses a date string into a LocalDate object.
     * Supports standard ISO format (YYYY-MM-DD) and relative dates (today, tomorrow, day names).
     *
     * @param dateString        - The date string to parse
     * @return                  - LocalDate object representing the parsed date
     * @throws PlopBotException - If the date string is empty or invalid
     */
    private static LocalDate parseDateString(String dateString) throws PlopBotException {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new PlopBotException("Date cannot be empty");
        }

        try {
            return parseStandardDate(dateString.trim());
        } catch (DateTimeParseException e) {
            try {
                return parseRelativeDate(dateString.trim());
            } catch (IllegalArgumentException ex) {
                throw new PlopBotException("Invalid date format. Use 'YYYY-MM-DD', 'today', 'tomorrow', or day name");
            }
        }
    }

    /**
     * Parses a date string in standard ISO format (YYYY-MM-DD).
     *
     * @param dateString - The date string to parse
     * @return           - LocalDate object representing the parsed date
     */
    private static LocalDate parseStandardDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Parses a relative date string (today, tomorrow, day names).
     *
     * @param dateString - The relative date string to parse
     * @return           - LocalDate object representing the parsed date
     */
    private static LocalDate parseRelativeDate(String dateString) {
        LocalDate now = LocalDate.now();
        String lowercaseDate = dateString.toLowerCase();

        if (lowercaseDate.equals("today")) return now;
        if (lowercaseDate.equals("tomorrow")) return now.plusDays(1);

        return parseDayOfWeek(dateString, now);
    }

    /**
     * Parses a day of week name into a date.
     *
     * @param dateString - Day name to parse
     * @param now        - Reference date for calculating the next occurrence
     * @return           - LocalDate object representing the next occurrence of the specified day
     */
    private static LocalDate parseDayOfWeek(String dateString, LocalDate now) {
        try {
            DayOfWeek day = DayOfWeek.valueOf(dateString.toUpperCase());
            return now.with(TemporalAdjusters.next(day));
        } catch (IllegalArgumentException e) {
            return parseAbbreviatedDay(dateString, now);
        }
    }

    /**
     * Parses an abbreviated day name (Mon, Tue, etc.).
     *
     * @param dateString                - Abbreviated day name to parse
     * @param now                       - Reference date for calculating the next occurrence
     * @return                          - LocalDate object representing the next occurrence of the specified day
     * @throws IllegalArgumentException - If the abbreviated day name is invalid
     */
    private static LocalDate parseAbbreviatedDay(String dateString, LocalDate now) {
        String[] shortDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        for (int i = 0; i < shortDays.length; i++) {
            if (shortDays[i].equalsIgnoreCase(dateString)) {
                return now.with(TemporalAdjusters.next(DayOfWeek.of(i + 1)));
            }
        }
        throw new IllegalArgumentException("Unable to parse date: " + dateString);
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     * Supports standard format (YYYY-MM-DD HH:mm) and relative formats.
     *
     * @param dateTimeString    - The date-time string to parse
     * @param referenceTime     - Optional reference time for relative parsing
     * @return                  - LocalDateTime object representing the parsed date-time
     * @throws PlopBotException - If the date-time string is empty or invalid
     */
    private static LocalDateTime parseDateTimeString(String dateTimeString, LocalDateTime referenceTime) throws PlopBotException {
        if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
            throw new PlopBotException("Date/time cannot be empty");
        }

        try {
            return LocalDateTime.parse(dateTimeString.trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e1) {
            try {
                return parseRelativeDateTime(dateTimeString.trim(), referenceTime);
            } catch (Exception e2) {
                throw new PlopBotException("Invalid time format. Use 'YYYY-MM-DD HH:mm' or 'DAY HHam/pm'");
            }
        }
    }

    /**
     * Parses a relative date-time string.
     *
     * @param dateTimeString    - The relative date-time string to parse
     * @param referenceTime     - Optional reference time for relative parsing
     * @return                  - LocalDateTime object representing the parsed date-time
     * @throws PlopBotException - If the parsing fails
     */
    private static LocalDateTime parseRelativeDateTime(String dateTimeString, LocalDateTime referenceTime) throws PlopBotException {
        String[] parts = dateTimeString.split(" ", 2);
        if (parts.length != 2) {
            throw new PlopBotException("Invalid date/time format. Use 'YYYY-MM-DD HH:mm' or 'DAY HHam/pm'");
        }

        LocalDate date = parseDateString(parts[0]);
        LocalTime time = parseTimeString(parts[1]);
        return LocalDateTime.of(date, time);
    }

    /**
     * Parses a time string in either 24-hour or AM/PM format.
     *
     * @param timeString        - The time string to parse
     * @return                  - LocalTime object representing the parsed time
     * @throws PlopBotException - If the time format is invalid
     */
    private static LocalTime parseTimeString(String timeString) throws PlopBotException {
        try {
            return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                return parseAmPmTime(timeString);
            } catch (Exception ex) {
                throw new PlopBotException("Invalid time format. Use 'HH:mm' (24-hour) or 'HHam/pm'");
            }
        }
    }

    /**
     * Parses a time string in AM/PM format.
     *
     * @param timeString        - The AM/PM time string to parse
     * @return                  - LocalTime object representing the parsed time
     * @throws PlopBotException - If the AM/PM time format is invalid
     */
    private static LocalTime parseAmPmTime(String timeString) throws PlopBotException {
        String formattedTime = timeString.toLowerCase();
        if (!formattedTime.endsWith("am") && !formattedTime.endsWith("pm")) {
            throw new PlopBotException("Invalid time format. Must end with 'am' or 'pm'");
        }

        try {
            int hour = Integer.parseInt(formattedTime.substring(0, formattedTime.length() - 2));
            boolean isPm = formattedTime.endsWith("pm");

            if (hour < 1 || hour > 12) {
                throw new PlopBotException("Hour must be between 1 and 12");
            }

            if (isPm && hour < 12) hour += 12;
            else if (!isPm && hour == 12) hour = 0;

            return LocalTime.of(hour, 0);
        } catch (NumberFormatException e) {
            throw new PlopBotException("Invalid time format: " + timeString);
        }
    }
}
