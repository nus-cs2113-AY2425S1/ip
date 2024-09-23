package command;

import exceptions.IrisException;
import task.Task;
import task.TaskList;

public class Parser {

    private static final String EMPTY_COMMAND_MESSAGE = "No command given";
    private static final String INVALID_COMMAND_MESSAGE = "Unrecognised or incomplete command";
    private static final String INVALID_TASK_NUMBER_MESSAGE = "This task does not exist";
    private static final String INVALID_TASK_NUMBER_FORMAT_MESSAGE = "The index of the task must be an integer";

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
