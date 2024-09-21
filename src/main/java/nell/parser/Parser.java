package nell.parser;

import nell.TaskList;
import nell.command.Command;
import nell.command.DeadlineCommand;
import nell.command.EventCommand;
import nell.command.IncorrectCommand;
import nell.command.ListCommand;
import nell.command.MarkCommand;
import nell.command.RemoveCommand;
import nell.command.ToDoCommand;
import nell.command.UnmarkCommand;
import nell.common.Messages;
import nell.tasks.Deadline;
import nell.tasks.Event;
import nell.tasks.ToDo;

/**
 * Handles the parsing and execution of commands entered by the user into the UI
 */
public class Parser {
    private TaskList tasks;

    /**
     * Constructs a parser object with a given task list
     *
     * @param tasks The task list to be used by the object
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses a given command and executes it.
     * If the command is invalid, prints a message that it is so.
     *
     * @param command The command to be parsed and executed
     */
    public Command parseCommand(String command) {
        String[] commandWords = command.split(" ", 2);
        switch (commandWords.length) {
        case 1:
            return parseSingleWordCommand(command);

        case 2:
            return parseMultiWordCommand(commandWords);

        default:
            return new IncorrectCommand(tasks);
        }
    }

    /**
     * Parses a given single-word command and executes it.
     * If the command is invalid, prints a message that it is so.
     *
     * @param command The command word of the single-word command
     */
    private Command parseSingleWordCommand(String command) {
        switch (command) {
        case "list":
            return new ListCommand(tasks);

        default:
            return new IncorrectCommand(tasks);
        }
    }

    /**
     * Parses a given multi-word command and executes it.
     * If the command is invalid, prints a message that it is so.
     *
     * @param commandWords The command word and body of the multi-word command
     */
    private Command parseMultiWordCommand(String[] commandWords) {
        switch (commandWords[0]) {
        case "mark":
            try {
                int taskIndex = Integer.parseInt(commandWords[1]);
                return new MarkCommand(tasks, taskIndex);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.MARK_ERROR_MESSAGE);
                break;
            } catch (NumberFormatException e) {
                System.out.print(Messages.MARK_ERROR_MESSAGE);
                break;
            }

        case "unmark":
            try {
                int taskIndex = Integer.parseInt(commandWords[1]);
                return new UnmarkCommand(tasks, taskIndex);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.UNMARK_ERROR_MESSAGE);
                break;
            } catch (NumberFormatException e) {
                System.out.print(Messages.UNMARK_ERROR_MESSAGE);
                break;
            }

        case "todo":
            try {
                return new ToDoCommand(tasks, commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.TODO_ERROR_MESSAGE);
                break;
            }

        case "deadline":
            try {
                return new DeadlineCommand(tasks, commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.DEADLINE_ERROR_MESSAGE);
                break;
            }

        case "event":
            try {
                return new EventCommand(tasks, commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.EVENT_ERROR_MESSAGE);
                break;
            }

        case "remove":
            try {
                int taskIndex = Integer.parseInt(commandWords[1]);
                return new RemoveCommand(tasks, taskIndex);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.REMOVE_ERROR_MESSAGE);
                break;
            } catch (NumberFormatException e) {
                System.out.print(Messages.REMOVE_ERROR_MESSAGE);
                break;
            }

        default:
            return new IncorrectCommand(tasks);
        }

        return new IncorrectCommand(tasks);
    }
}
