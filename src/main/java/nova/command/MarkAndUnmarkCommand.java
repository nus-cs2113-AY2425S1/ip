package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InvalidInputException;
import nova.task.Task;

public class MarkAndUnmarkCommand extends Command{

    public static final String COMMAND_WORD1 = "mark";
    public static final String COMMAND_WORD2 = "unmark";

    public static void execute(String[] inputs, TaskList taskManager) {
        int taskIndex;
        try {
            taskIndex = validateIndex(inputs);
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage());
            return;
        }

        if (inputs[0].equals("mark")) {
            taskManager.markTask(taskIndex - 1);
            Ui.displayMarkMessage(taskManager.getTask(taskIndex - 1));
        } else {
            taskManager.unmarkTask(taskIndex - 1);
            Ui.displayUnmarkMessage(taskManager.getTask(taskIndex - 1));
        }
        taskManager.updateStorage();
    }

    protected static int validateIndex(String[] inputs) throws InvalidInputException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid index. Please provide a valid task number after the command.");
        }

        if (taskIndex <= 0 || taskIndex > Task.getNumberOfTasks()) {
            throw new InvalidInputException("Invalid task index: " + taskIndex + ". " +
                    "Please provide a number between 1 and " + Task.getNumberOfTasks() + ".");
        }

        return taskIndex;
    }
}
