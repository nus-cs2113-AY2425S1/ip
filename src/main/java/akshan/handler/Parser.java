package akshan.handler;

import akshan.command.*;
import akshan.task.TaskList;
import java.util.Optional;

public class Parser {
    /**
     * Processes the user's command.
     *
     * @param input The user's input command.
     * @param taskList The list of tasks.
     * @throws IllegalArgumentException If the command is unknown or invalid.
     */
    public static void processCommand(String input, TaskList taskList) throws IllegalArgumentException {
        String[] commandString = input.split(" ", 2);
        CommandType commandType;
        try {
            commandType = CommandType.fromString(commandString[0]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown command: " + commandString[0]);
        }

        if (commandType == CommandType.LIST) {
            taskList.printList();
        }
        else {
            Optional<Command> command = Optional.empty();

            switch (commandType) {
            case MARK:
                // Fallthrough
            case UNMARK:
                command = Optional.of(new MarkUnmarkCommand(commandType, commandString[1], taskList));
                break;
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                if (commandString.length < 2) {
                    throw new IllegalArgumentException("Invalid task command. Please incldue task string.");
                }
                command = Optional.of(new TaskCommand(commandType, commandString[1], taskList));
                break;
            case DELETE:
                command = Optional.of(new DeleteCommand(commandType, commandString[1], taskList));
                break;
            case DATE:
                command = Optional.of(new DateCommand(commandType, commandString[1], taskList));
                break;
            case FIND:
                command = Optional.of(new FindCommand(commandType, commandString[1], taskList));
                break;
            default:
                throw new IllegalArgumentException("Uh oh, no command found in: " + commandString[0]);
            }
            command.ifPresent(Command::execute);
        }
    }
}
