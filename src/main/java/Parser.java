import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

public class Parser {
    public String[] parseCommand(String userInput) throws PlopBotException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length == 0) {
            throw new PlopBotException("Empty command");
        }
        return parts;
    }

    /**
     * Method that parses tasks into their respective types based on the user's inputs.
     * @param commandParts
     * @return
     * @throws PlopBotException
     */
    public Task parseTask(String[] commandParts) throws PlopBotException {
        if (commandParts.length < 2) {
            throw new PlopBotException("Invalid task format");
        }
        String type = commandParts[0];
        String details = commandParts[1];

        switch (type) {
            case "todo":
                return new Task(details);
            case "deadline":
                String[] deadlineParts = details.split(" /by ", 2);
                if (deadlineParts.length != 2) {
                    throw new PlopBotException("Invalid deadline format");
                }
                return new Task(deadlineParts[0], parseDateString(deadlineParts[1]));
            case "event":
                String[] eventParts = details.split(" /from | /to ");
                if (eventParts.length != 3) {
                    throw new PlopBotException("Invalid event format");
                }
                LocalDateTime startTime = parseDateTimeString(eventParts[1], null);
                LocalDateTime endTime = parseDateTimeString(eventParts[2], startTime);
                return new Task(eventParts[0], startTime, endTime);
            default:
                throw new PlopBotException("Unknown task type: " + type);
        }
    }

    /**
     * Parse helper method to parse date strings for deadline tasks.
     * @param dateString
     * @return
     */
    private static LocalDate parseDateString(String dateString) {
        LocalDate now = LocalDate.now();
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            String[] shortDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

            for (int i = 0; i < shortDays.length; i++) {
                if (shortDays[i].equalsIgnoreCase(dateString)) {
                    return now.with(TemporalAdjusters.next(DayOfWeek.of(i + 1)));
                }
            }
            try {
                return now.with(TemporalAdjusters.next(DayOfWeek.valueOf(dateString.toUpperCase())));
            } catch (IllegalArgumentException ex) {
                switch (dateString.toLowerCase()) {
                    case "today":
                        return now;
                    case "tomorrow":
                        return now.plusDays(1);
                    default:
                        throw new IllegalArgumentException("Unable to parse date: " + dateString);
                }
            }
        }
    }

    /**
     * Parse helper method to parse date and time strings for event tasks.
     * @param dateTimeString
     * @return
     */
    private static LocalDateTime parseDateTimeString(String dateTimeString, LocalDateTime referenceTime) {
        LocalDate now = LocalDate.now();
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            String[] parts = dateTimeString.split(" ", 2);

            if (parts.length == 2) {
                LocalDate date = parseDateString(parts[0]);
                LocalTime time = parseTimeString(parts[1]);
                return LocalDateTime.of(date, time);
            } else if (parts.length == 1) {
                LocalTime time = parseTimeString(parts[0]);
                LocalDate date = (referenceTime != null) ? referenceTime.toLocalDate() : now;
                return LocalDateTime.of(date, time);
            }
        }
        throw new IllegalArgumentException("Unable to parse date and time: " + dateTimeString);
    }

    /**
     * Parse helper method to parse time strings.
     * @param timeString
     * @return
     */
    private static LocalTime parseTimeString(String timeString) {
        try {
            return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                return LocalTime.parse(timeString.toUpperCase(), DateTimeFormatter.ofPattern("ha"));
            } catch (DateTimeParseException e2) {
                // Handle "2pm" format
                String formattedTime = timeString.toLowerCase();
                if (formattedTime.endsWith("am") || formattedTime.endsWith("pm")) {
                    int hour = Integer.parseInt(formattedTime.substring(0, formattedTime.length() - 2));

                    if (formattedTime.endsWith("pm") && hour < 12) {
                        hour += 12;
                    } else if (formattedTime.endsWith("am") && hour == 12) {
                        hour = 0;
                    }
                    return LocalTime.of(hour, 0);
                }
                throw new IllegalArgumentException("Unable to parse time: " + timeString);
            }
        }
    }
}