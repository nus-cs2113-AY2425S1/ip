package bron.parser;

import bron.command.Command;
import bron.task.Deadline;
import bron.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static LocalDateTime parseDeadline(String dateTimeStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeStr.trim(), formatter);
    }

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