package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to list tasks in the task manager.
 * The 'list' command can display all tasks or filter tasks by a specific date if provided.
 */
public class ListCommand extends Command {

    /**
     * The command word for the List command.
     */
    public static final String COMMAND_WORD = "list";
    private static final String LIST_ALL_MESSAGE = "Listing all tasks";
    private static final String LIST_TASK_DATE_MESSAGE = "Listing all tasks on ";
    private static final String SPACING = "\n     ";

    /**
     * Executes the 'list' command. If a date is provided, it lists all tasks scheduled on that date.
     * Otherwise, it lists all tasks in the task manager.
     *
     * @param inputs The user input containing the 'list' command and an optional date.
     * @param taskManager The task manager that holds the current tasks.
     */
    public static void execute(String[] inputs, TaskList taskManager) {
        try {
            LocalDate date = LocalDate.parse(inputs[1]);
            listDate(taskManager, date);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            listAll(taskManager);
        }
    }

    /**
     * Lists all tasks that are scheduled for the given date.
     *
     * @param taskList The task manager containing the tasks.
     * @param date The date to filter tasks by.
     */
    private static void listDate(TaskList taskList, LocalDate date) {
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

    /**
     * Lists all tasks currently in the task manager, regardless of date.
     *
     * @param taskList The task manager containing the tasks.
     */
    private static void listAll(TaskList taskList) {
        String result = LIST_ALL_MESSAGE;
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            result += SPACING + (i + 1) + "." + taskList.getTaskInfo(i);
        }
        Ui.displayMessage(result);
    }
}
