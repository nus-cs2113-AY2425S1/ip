package bron.parser;

import bron.command.Command;
import bron.task.Deadline;
import bron.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class provides utility methods to parse various inputs such as commands and task data.
 * It also includes methods to parse deadlines and reconstruct tasks from file data.
 */
public class Parser {

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTimeStr the date-time string in the format yyyy-MM-dd HHmm
     * @return a LocalDateTime object representing the parsed date and time
     * @throws DateTimeParseException if the date-time string is not in the correct format
     */
    public static LocalDateTime parseDeadline(String dateTimeStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeStr.trim(), formatter);
    }

    /**
     * Parses a string representing a task and converts it into the corresponding Task object.
     *
     * @param line the string to be parsed, expected to be in the format saved in the file
     * @return a Task object (ToDo, Deadline, or Event) based on the parsed data, or null if an error occurs
     */
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            System.out.println("Error parsing line: " + line);
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            if (parts.length < 4) {
                System.out.println("Error parsing Deadline task (too few parts): " + line);
                return null;
            }
            String by = parts[3];
            LocalDateTime byDateTime;
            try {
                byDateTime = parseDeadline(by);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for Deadline task: " + by);
                return null;
            }
            return new Deadline(description, byDateTime, isDone);
        case "E":
            if (parts.length < 5) {
                System.out.println("Error parsing Event task (too few parts): " + line);
                return null;
            }
            String from = parts[3];
            String to = parts[4];
            return new Event(description, from, to, isDone);
        default:
            System.out.println("Unknown task type: " + type);
            return null;
        }
    }

    /**
     * Parses a user input string and returns the corresponding Command enum.
     *
     * @param input the user input string
     * @return the parsed Command, or Command.UNKNOWN if the input is not a valid command
     */
    public static Command parseCommand(String input) {
        String[] parts = input.split(" ");
        String commandStr = parts[0].toLowerCase();  // Extract the first word as command

        try {
            return Command.valueOf(commandStr.toUpperCase());  // Convert to enum
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;  // Return UNKNOWN if invalid command
        }
    }

}