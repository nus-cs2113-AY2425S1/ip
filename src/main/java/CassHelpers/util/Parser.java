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
import java.util.Scanner;

public class Parser {

    public static String getUserInput(){
        return new Scanner(System.in).nextLine().trim();
    }
    
    public static String[] getCommandArgsFromUserInput(String input){
        return input.split(" ");
    }

    public static LocalDateTime getNextDayOfWeek(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        LocalDate nextOrSameDay = today.with(TemporalAdjusters.nextOrSame(dayOfWeek));
        return LocalDateTime.of(nextOrSameDay, LocalTime.of(0, 0));  // Set time to 12 AM by default
    }

    public static LocalDateTime parseDateTime(String by) throws InvalidDeadlineFormatException {
        // Check if user entered natural language for day (e.g., "monday" or "next monday")

        if (by.equalsIgnoreCase("monday") || by.equalsIgnoreCase("next monday")) {
            return getNextDayOfWeek(DayOfWeek.MONDAY);
        } else if (by.equalsIgnoreCase("tuesday") || by.equalsIgnoreCase("next tuesday")) {
            return getNextDayOfWeek(DayOfWeek.TUESDAY);
        } else if (by.equalsIgnoreCase("wednesday") || by.equalsIgnoreCase("next wednesday")) {
            return getNextDayOfWeek(DayOfWeek.WEDNESDAY);
        } else if (by.equalsIgnoreCase("thursday") || by.equalsIgnoreCase("next thursday")) {
            return getNextDayOfWeek(DayOfWeek.THURSDAY);
        } else if (by.equalsIgnoreCase("friday") || by.equalsIgnoreCase("next friday")) {
            return getNextDayOfWeek(DayOfWeek.FRIDAY);
        } else if (by.equalsIgnoreCase("saturday") || by.equalsIgnoreCase("next saturday")) {
            return getNextDayOfWeek(DayOfWeek.SATURDAY);
        } else if (by.equalsIgnoreCase("sunday") || by.equalsIgnoreCase("next sunday")) {
            return getNextDayOfWeek(DayOfWeek.SUNDAY);
        } else {
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
    }

    public static LocalDateTime parseStringToLocalDateTime(String dateString) {
        try {
            return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use 'dd-MM-yyyy-HH-mm'.");
        }
    }


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
            case "find" -> new FindCommand(tasks,input);
            case "help" -> new HelpCommand(ui);
            default -> throw new InvalidCommandException("Sorry, unknown command.");
        };
    }

}

