package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to list all tasks in the task manager.
 * This command retrieves and displays the current tasks for the user.
 */

public class ListCommand extends Command {

    /**
     * The command word for the List command.
     */
    public static final String COMMAND_WORD = "list";
    public static final String LIST_ALL_MESSAGE = "Listing all tasks";
    public static final String LIST_TASK_DATE_MESSAGE = "Listing all task on ";
    public static final String SPACING = "\n     ";


    public static void execute(String[] inputs, TaskList taskManager) {
        try {
            LocalDate date = LocalDate.parse(inputs[1]);
            listDate(taskManager, date);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            listAll(taskManager);
        }

    }

    public static void listDate(TaskList taskList, LocalDate date) {
        String result = LIST_TASK_DATE_MESSAGE + date;
        int counter = 1;
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            if (taskList.isDate(i, date)) {
                result += SPACING + counter + "." + taskList.getTaskInfo(i);
                counter++;
            }
        }
        Ui.displayMessage(result);
    }

    public static void listAll(TaskList taskList) {
        String result = LIST_ALL_MESSAGE;
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            result += SPACING + (i + 1) + "." + taskList.getTaskInfo(i);
        }
        Ui.displayMessage(result);
    }
}
