package parser;

import exception.LiaException;
import commands.Command;
import commands.AddCommand;
import commands.DeleteCommand;
import commands.MarkCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.FindCommand;


/**
 * Parses user input commands.
 */
public class Parser {

    public static Command parse(String commandString) throws LiaException {
        String[] parts = commandString.split(" ", 2);
        String command = parts[0];

        switch (command.toLowerCase()) {
        case "todo":
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new LiaException("Oops! The description of a todo cannot be empty.");
            }
            return new AddCommand(commandString);
        case "deadline":
            if (parts.length < 2 || !parts[1].contains("/by")) {
                throw new LiaException("Oops! The deadline command requires a description and '/by' followed by a date.");
            }
            return new AddCommand(commandString);
        case "event":
            if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                throw new LiaException("Oops! The event command requires a description, '/from', and '/to' followed by times.");
            }
            return new AddCommand(commandString);
        case "mark":
            if (parts.length < 2) {
                throw new LiaException("Oops! You must specify a task number to mark.");
            }
            return new MarkCommand(parts[1], true);
        case "unmark":
            if (parts.length < 2) {
                throw new LiaException("Oops! You must specify a task number to unmark.");
            }
            return new MarkCommand(parts[1], false);
        case "delete":
            if (parts.length < 2) {
                throw new LiaException("Oops! You must specify a task number to delete.");
            }
            return new DeleteCommand(parts[1]);
        case "find":
            if (parts.length < 2) {
                throw new LiaException("Oops! You must specify a keyword to find.");
            }
            return new FindCommand(parts[1]);
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        default:
            throw new LiaException("Oops! I don't recognize that command.");
        }
    }
}
