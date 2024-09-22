package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InsufficientSpaceException;
import nova.exception.InvalidInputException;
import nova.task.Todo;

public class TodoCommand extends Command{

    public static final String COMMAND_WORD = "todo";
    private static final String TODO_USAGE = "Usage: todo <task description>.";

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

    protected static void validateTodoInput(String[] inputs) throws InvalidInputException {
        if (inputs.length != 2) {
            throw new InvalidInputException("No description entered.");
        }
    }
}
