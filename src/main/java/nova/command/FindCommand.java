package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InvalidInputException;
import nova.task.Task;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String FIND_USUAGE = "Usage: find <phrase>";
    public static final String SPACING = "\n     ";
    public static final String FIND_MESSAGE = "All the tasks that contain the word '";

    public static void execute(String[] inputs, TaskList taskList) {
        try {
            validateInput(inputs);
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage(), FIND_USUAGE);
        }
        int counter = 1;
        String result = FIND_MESSAGE + inputs[1] + "'";
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            if (taskList.doesContain(i, inputs[1])) {
                result += SPACING + counter + "." + taskList.getTaskInfo(i);
                counter++;
            }
        }
        Ui.displayMessage(result);
    }

    private static void validateInput(String[] inputs) throws InvalidInputException {
        if (inputs.length == 1) {
            throw new InvalidInputException("No arguments given");
        }
    }
}
