package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InvalidInputException;
import nova.task.Task;

/**
 * Handles the execution of the 'find' command which searches for tasks containing
 * a specific phrase provided by the user.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String FIND_USAGE = "Usage: find <phrase>";
    public static final String SPACING = "\n     ";
    public static final String FIND_MESSAGE = "All the tasks that contain the word '";

    /**
     * Executes the 'find' command. Searches for tasks containing the given phrase and displays them to the user.
     *
     * @param inputs The user input containing the command and the phrase to search for.
     * @param taskList The list of tasks to search within.
     */
    public static void execute(String[] inputs, TaskList taskList) {
        try {
            validateInput(inputs);
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage(), FIND_USAGE);
        }

        String result = FIND_MESSAGE + inputs[1] + "'";
        result = findTasks(inputs, taskList, result);
        Ui.displayMessage(result);
    }

    /**
     * Appends task that contain the specified phrase to the result string.
     *
     * @param inputs The input arguments provided by the user.
     * @param taskList The task list to search within.
     * @param result The string to store and display matching tasks.
     * @return The final result string with all matching tasks.
     */
    private static String findTasks(String[] inputs, TaskList taskList, String result) {
        int counter = 1;
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            if (taskList.doesContain(i, inputs[1])) {
                result += SPACING + counter + "." + taskList.getTaskInfo(i);
                counter++;
            }
        }
        return result;
    }

    /**
     * Validates the user input for the 'find' command, ensuring the phrase is provided.
     *
     * @param inputs The user input array to validate.
     * @throws InvalidInputException if the input does not contain a phrase to search for.
     */
    private static void validateInput(String[] inputs) throws InvalidInputException {
        if (inputs.length == 1) {
            throw new InvalidInputException("No arguments given");
        }
    }
}
