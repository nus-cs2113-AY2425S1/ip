/**
 * Represents the command to mark or unmark a task as done or not done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean isMark;

    /**
     * Constructs a MarkCommand with the specified task index and marking status.
     *
     * @param index  The index of the task to mark/unmark.
     * @param isMark If true, marks the task as done; otherwise, marks it as not done.
     * @throws BebeException if the index is not a valid number.
     */
    public MarkCommand(String index, boolean isMark) {
        this.taskIndex = Integer.parseInt(index) - 1; // Convert from user input (1-based index) to 0-based index.
        this.isMark = isMark;
    }

    /**
     * Executes the mark command to either mark or unmark a task in the task list.
     *
     * @param tasks   The list of tasks where the task is located.
     * @param ui      The UI object to display results to the user.
     * @param storage The storage object to save tasks.
     * @throws BebeException if the task index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        try {
            if (isMark) {
                tasks.markTask(taskIndex); // Mark the task as done
                ui.show("Nice! I've marked this task as done:\n  " + tasks.getTasks().get(taskIndex));
            } else {
                tasks.unmarkTask(taskIndex); // Unmark the task
                ui.show("OK, I've marked this task as not done yet:\n  " + tasks.getTasks().get(taskIndex));
            }
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task does not exist.");
        }
    }
}