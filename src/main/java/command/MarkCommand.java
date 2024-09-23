package command;

import data.Storage;
import exceptions.IrisException;
import task.Task;
import task.TaskList;

/**
 * The MarkCommand class handles marking a task as completed or uncompleted
 * based on the command given by the user. It toggles the task's status in the
 * task list and displays the appropriate message.
 * <p>
 * The class supports two actions:
 * <li>Marking a task as done.</li>
 * <li>Unmarking a task as not done.</li>
 *
 * @author Tan Ping Hui
 */
public class MarkCommand extends Command {
    private static final String MARK_AS_COMPLETED_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARK_FROM_COMPLETED_MESSAGE = "OK, I've marked this task as not done yet:";

    private final boolean isMark;
    private final String description;

    /**
     * Constructs a MarkCommand object, determining whether to mark or unmark
     * a task based on the command given.
     *
     * @param command The command string that specifies the action ("mark" or "unmark").
     * @param description The description containing the task number to be marked/unmarked.
     */
    public MarkCommand(String command, String description) {
        this.isMark = command.equals("mark");
        this.description = description;
    }

    /**
     * Executes the command to toggle the status of the specified task in the task list.
     * Marks the task as done or undone based on the provided command and displays
     * the corresponding status message.
     *
     * @param tasks The task list that contains the task to be marked or unmarked.
     * @param storage The storage to save the updated task list.
     * @throws IrisException If the task number is invalid or if an error occurs during parsing.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws IrisException {
        Task taskToChange = Parser.getTaskNum(tasks, description);
        taskToChange.mark(isMark);
        printChangeTaskStatusMessage(taskToChange, isMark);
    }

    /**
     * Prints a message indicating whether the task was marked as done or undone,
     * along with the task details.
     *
     * @param task The task whose status was changed.
     * @param isMark Indicates if the task was marked as done (true) or undone (false).
     */
    private static void printChangeTaskStatusMessage(Task task, boolean isMark) {
        if (isMark) {
            System.out.println(MARK_AS_COMPLETED_MESSAGE);
        } else {
            System.out.println(UNMARK_FROM_COMPLETED_MESSAGE);
        }
        System.out.println(task);
    }
}
