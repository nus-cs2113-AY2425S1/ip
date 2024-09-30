package CassHelpers.util;

import CassHelpers.commands.*;
import CassHelpers.exceptions.InvalidCommandException;
import CassHelpers.exceptions.InvalidDateFormatException;
import CassHelpers.exceptions.InvalidDeadlineFormatException;
import CassHelpers.exceptions.NoCommandException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.Scanner;

/**
 * Parser class that contains utility methods for parsing user input and date/time strings,
 * and mapping input commands to the corresponding Command classes.
 */
public class Parser {

    // Map for associating day strings to DayOfWeek enum values
    static final Map<String, DayOfWeek> DAY_MAP = Map.of(
            "monday", DayOfWeek.MONDAY,
            "tuesday", DayOfWeek.TUESDAY,
            "wednesday", DayOfWeek.WEDNESDAY,
            "thursday", DayOfWeek.THURSDAY,
            "friday", DayOfWeek.FRIDAY,
            "saturday", DayOfWeek.SATURDAY,
            "sunday", DayOfWeek.SUNDAY
    );

    /**
     * Retrieves user input from the console.
     *
     * @return A trimmed string containing the user's input.
     */
    public static String getUserInput(){
        return new Scanner(System.in).nextLine().trim();
    }

    /**
     * Splits the user input into an array of command arguments.
     *
     * @param input The raw input string entered by the user.
     * @return An array of strings representing the individual command arguments.
     */
    public static String[] getCommandArgsFromUserInput(String input){
        return input.split(" ");
    }

    /**
     * Gets the next occurrence of a specified day of the week.
     *
     * @param dayOfWeek The day of the week to find.
     * @return A LocalDate representing the next occurrence of the specified day.
     */
    public static LocalDate getNextDayOfWeek(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        LocalDate nextOrSameDay = today.with(TemporalAdjusters.nextOrSame(dayOfWeek));
        return nextOrSameDay;
    }

    /**
     * Parses a string input to determine a deadline date and time.
     * Supports natural language (e.g., "monday", "next monday") and specific date/time formats.
     *
     * @param by The user input specifying the deadline.
     * @return A LocalDateTime object representing the parsed deadline.
     * @throws InvalidDeadlineFormatException if the input does not match an expected format.
     */
    public static LocalDateTime parseDateTime(String by) throws InvalidDeadlineFormatException {

        // Regex to match inputs like "next monday 1600" or "wednesday 1400"
        String dayWithTimePattern = "(next )?(monday|tuesday|wednesday|thursday|friday|saturday|sunday) \\d{4}";

        if (by.matches(dayWithTimePattern)) {
            String[] parts = by.split(" ");
            boolean isNext = parts.length == 3 || parts[0].equalsIgnoreCase("next");
            String dayPart = isNext ? parts[1] : parts[0];
            String timePart = isNext ? parts[2] : parts[1];

            LocalTime time = LocalTime.parse(timePart, DateTimeFormatter.ofPattern("HHmm"));
            DayOfWeek dayOfWeek = DAY_MAP.get(dayPart.toLowerCase());

            return LocalDateTime.of(getNextDayOfWeek(dayOfWeek), time);
        }

        // If only day without time (e.g., "monday" or "next monday")
        if (DAY_MAP.containsKey(by.toLowerCase()) || by.toLowerCase().startsWith("next ")) {
            String dayPart = by.toLowerCase().replace("next ", "").trim();
            DayOfWeek dayOfWeek = DAY_MAP.get(dayPart);
            return LocalDateTime.of(getNextDayOfWeek(dayOfWeek), LocalTime.of(0, 0)); // Set default time as 12 AM
        }

        try {
            if (by.matches("\\d{1,2}-\\d{1,2}-\\d{4} \\d{4}")) {
                // If user provided date and time
                return LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            } else if (by.matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
                // If user provided only date
                LocalDate parsedDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                return LocalDateTime.of(parsedDate, LocalTime.of(0, 0));  // Default time 12:00 AM
            } else {
                throw new InvalidDateFormatException("Sorry, the date format is incorrect. Use dd-mm-yyyy or dd-mm-yyyy HHmm format or <day-of-the-week> or next <day-of-the-week>.");
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Sorry, the date format is incorrect. Use dd-MM-yyyy or dd-MM-yyyy HHmm format or <day-of-the-week> or next <day-of-the-week>.");
        }
    }

    /**
     * Parses a string in the format 'dd-MM-yyyy-HH-mm' into a LocalDateTime object.
     *
     * @param dateString The string representing the date and time.
     * @return A LocalDateTime object parsed from the input string.
     * @throws InvalidDateFormatException if the input string does not match the expected format.
     */
    public static LocalDateTime parseStringToLocalDateTime(String dateString) {
        try {
            return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use 'dd-MM-yyyy-HH-mm'.");
        }
    }

    /**
     * Parses the user's input into a corresponding command based on the input command keyword.
     *
     * @param input The user's raw input string.
     * @param commandArgs The parsed command arguments from the input.
     * @param ui The UI instance used to interact with the user.
     * @param tasks The TaskList instance containing the tasks to be managed.
     * @return A Command object representing the user's input command.
     * @throws NoCommandException If no command is provided in the input.
     * @throws InvalidCommandException If the input command is not recognized.
     */
    public static Command parse(String input, String[] commandArgs, UI ui, TaskList tasks) throws NoCommandException {
        if (commandArgs.length == 0 || commandArgs[0].isEmpty()) {
            throw new NoCommandException("Sorry, you haven't entered any command.");
        }
        return switch (commandArgs[0].toLowerCase()) {
            case "mark" -> new MarkCommand(tasks, Integer.parseInt(commandArgs[1]));
            case "unmark" -> new UnmarkCommand(tasks, Integer.parseInt(commandArgs[1]));
            case "delete" -> new DeleteCommand(tasks, Integer.parseInt(commandArgs[1]));
            case "list" -> new ListCommand(tasks);
            case "bye" -> new ExitCommand(tasks);
            case "todo" -> new AddTodoCommand(tasks, input);
            case "deadline" -> new AddDeadlineCommand(tasks, input);
            case "event" -> new AddEventCommand(tasks, input);
            case "find" -> new FindCommand(tasks, input);
            case "help" -> new HelpCommand(ui);
            default -> throw new InvalidCommandException("Sorry, unknown command.");
        };
    }
}
