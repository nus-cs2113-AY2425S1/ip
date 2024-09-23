package command;

import exceptions.IrisException;
import task.Task;
import task.TaskList;

/**
 * The Parser class is responsible for interpreting user commands and converting
 * them into executable {@link Command} objects. It also helps in identifying
 * tasks based on their index in the {@link TaskList}.
 *
 * @author Tan Ping Hui
 */
public class Parser {

    private static final String EMPTY_COMMAND_MESSAGE = "No command given";
    private static final String INVALID_COMMAND_MESSAGE = "Unrecognised or incomplete command";
    private static final String INVALID_TASK_NUMBER_MESSAGE = "This task does not exist";
    private static final String INVALID_TASK_NUMBER_FORMAT_MESSAGE = "The index of the task must be an integer";

    /**
     * Parses the full command entered by the user and converts it into the corresponding
     * {@link Command} object for execution. If the command is not recognized or incomplete,
     * an {@link IrisException} is thrown.
     *
     * @param fullCommand The complete command string entered by the user.
     * @return The corresponding {@link Command} object based on the user's input.
     * @throws IrisException If the command is invalid or unrecognized.
     */
    public static Command parse(String fullCommand) throws IrisException {
        String[] commandParts = fullCommand.split(" ", 2);
        String command = commandParts[0].toLowerCase();
        String description;

        switch (command) {
        case "":
            throw new IrisException(EMPTY_COMMAND_MESSAGE);
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        default:
            if (commandParts.length == 1) { // No descriptions when other commands need it
                throw new IrisException(INVALID_COMMAND_MESSAGE);
            }
            description = commandParts[1];
        }

        switch (command) {
        case "find":
            return new FindCommand(description);
        case "date":
            return new DateCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "mark", "unmark":
            return new MarkCommand(command, description);
        case "deadline", "todo", "event":
            return new AddCommand(command, description);
        default: // Unrecognised command
            throw new IrisException(INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Retrieves a {@link Task} from the given {@link TaskList} based on the index provided
     * in the description. The description is expected to contain the index as a string, which
     * is then converted to an integer.
     *
     * @param tasks The list of tasks from which the task is to be retrieved.
     * @param description The description containing the task number as a string.
     * @return The {@link Task} corresponding to the provided task number.
     * @throws IrisException If the task number is invalid or if the format is incorrect.
     */
    public static Task getTaskNum(TaskList tasks, String description) throws IrisException {
        try {
            String filteredDescription = description.trim();
            int taskIndex = Integer.parseInt(filteredDescription) - 1;
            boolean isInvalidTaskIndex = taskIndex >= tasks.size() || taskIndex < 0;
            if (isInvalidTaskIndex) {
                throw new IrisException(INVALID_TASK_NUMBER_MESSAGE);
            }
            return tasks.get(taskIndex);
        } catch (NumberFormatException e) { // from parseInt
            throw new IrisException(INVALID_TASK_NUMBER_FORMAT_MESSAGE);
        }
    }
}
