package bron.parser;

import bron.command.Command;

public class Parser {

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