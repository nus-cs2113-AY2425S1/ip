package org.ajay.parser;

import java.util.Scanner;
import org.ajay.commands.Command;
import org.ajay.commands.DeadlineCommand;
import org.ajay.commands.DeleteCommand;
import org.ajay.commands.EventCommand;
import org.ajay.commands.ExitCommand;
import org.ajay.commands.FindCommand;
import org.ajay.commands.HelpCommand;
import org.ajay.commands.ListCommand;
import org.ajay.commands.MarkCommand;
import org.ajay.commands.TodoCommand;
import org.ajay.commands.UnmarkCommand;
import org.ajay.common.Constants;
import org.ajay.common.Messages;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.Error;
import org.ajay.ui.TextUi;

/**
 * Represents the parser to parse the input from the user.
 */
public class Parser {
    public static String command = ""; // Variable to store the command
    public static String task = ""; // Variable to store the task

    /**
     * Splits the command and task from the input.
     *
     * @param lineBufferString The input from the user
     */
    public static void splitCommandAndTask(String lineBufferString) throws EmptyArgumentException {
        if (lineBufferString.isEmpty()) {
            throw new EmptyArgumentException(Messages.MESSAGE_LINE_STRING_EMPTY + " " + Error.EMPTY_ARG.toString());
        } else {
            lineBufferString = lineBufferString.trim();
        }

        command = "";
        task = "";

        /** Check if the command is a single word command */
        boolean isSingleCommand = (lineBufferString.equals(ListCommand.COMMAND_WORD)
                || lineBufferString.equals(ExitCommand.COMMAND_WORD)
                || lineBufferString.equals(HelpCommand.COMMAND_WORD)
                || lineBufferString.equals(Constants.EXIT_COMMAND_ALT));

        if (lineBufferString.contains(" ")) {
            command = lineBufferString.split(" ")[0];
            task = lineBufferString.substring(command.length() + 1);
        } else if (isSingleCommand) {
            command = lineBufferString;
        } else {
            throw new EmptyArgumentException(Error.INVAILD_COMMAND_FORMAT.toString());
        }
    }

    /**
     * Reads the input from the user and processes it.
     *
     * @param in               Scanner object to read input from the user
     * @param lineBufferString The input from the user
     */
    public static void readInput(Scanner in) {
        try {
            TextUi.printPrompt(); // Print the prompt to the console
            String lineBufferString = in.nextLine();
            splitCommandAndTask(lineBufferString);
        } catch (EmptyArgumentException e) {
            TextUi.printExceptions(e.getMessage());
        }
    }

    /**
     * Parses the command and returns the appropriate command object.
     *
     * @param command The command from the user
     * @param task    The task from the user
     *
     * @return Command object
     */
    public static Command parseCommand(String command, String task) {
        switch (command) {
            case ExitCommand.COMMAND_WORD:
                /** Exit the program */
                return new ExitCommand();
            case Constants.EXIT_COMMAND_ALT:
                /** Alternative exit to exit the program */
                return new ExitCommand();
            case ListCommand.COMMAND_WORD:
                /** List all the tasks */
                return new ListCommand();
            case TodoCommand.COMMAND_WORD:
                /** Add a todo task */
                return new TodoCommand();
            case DeadlineCommand.COMMAND_WORD:
                /** Add a deadline task */
                return new DeadlineCommand();
            case EventCommand.COMMAND_WORD:
                /** Add an event task */
                return new EventCommand();
            case MarkCommand.COMMAND_WORD:
                /** Mark the task as done */
                return new MarkCommand();
            case UnmarkCommand.COMMAND_WORD:
                /** Mark the task as undone */
                return new UnmarkCommand();
            case DeleteCommand.COMMAND_WORD:
                /** Delete the task */
                return new DeleteCommand();
            case HelpCommand.COMMAND_WORD:
                /** Print the help message */
                return new HelpCommand();
            case FindCommand.COMMAND_WORD:
                /** Find the task */
                return new FindCommand();
            default:
                return new HelpCommand();
        }
    }
}
