package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InsufficientSpaceException;
import nova.exception.InvalidInputException;
import nova.task.Todo;

/**
 * Represents a command to add a Todo task to the task manager.
 * This command allows users to create tasks that do not have a specific deadline.
 */
public class TodoCommand extends Command {

    /**
     * The command word for adding a Todo task.
     */
    public static final String COMMAND_WORD = "todo";
    private static final String TODO_USAGE = "Usage: todo <task description>.";

    /**
     * Executes the todo command to add a new Todo task.
     *
     * @param inputs The user input containing the command and task description.
     * @param taskManager The TaskList instance managing tasks.
     */
    public static void execute(String[] inputs, TaskList taskManager) {
        try {
            validateTodoInput(inputs);
            taskManager.checkSpace();
            taskManager.addTask(new Todo(inputs[1]));
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage(), TODO_USAGE);
        } catch (InsufficientSpaceException e) {
            Ui.displayInvalidInputMessage(e.getMessage());
        }
    }

    /**
     * Validates the input provided for the Todo command.
     *
     * @param inputs The user input containing the command and task description.
     * @throws InvalidInputException If the input is invalid.
     */
    private static void validateTodoInput(String[] inputs) throws InvalidInputException {
        if (inputs.length != 2) {
            throw new InvalidInputException("No description entered.");
        }
    }
}
