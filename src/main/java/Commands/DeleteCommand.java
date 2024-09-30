package Commands;

import Tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 * The command requires the index of the task to be deleted.
 * 
 * <p>Usage:</p>
 * <pre>
 * delete <index>
 * </pre>
 * 
 * <p>Example:</p>
 * <pre>
 * delete 1
 * </pre>
 * 
 * <p>Upon successful execution, the command removes the task at the specified index
 * from the task list and returns a success message. If the task cannot be deleted,
 * an error message is returned.</p>
 * 
 * <p>Fields:</p>
 * <ul>
 *   <li>{@code COMMAND_WORD} - The command word for deletion ("delete").</li>
 *   <li>{@code MESSAGE_USAGE} - The usage message for the delete command.</li>
 *   <li>{@code MESSAGE_SUCCESS} - The success message format for the delete command.</li>
 *   <li>{@code toDelete} - The index of the task to be deleted.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code DeleteCommand(int index)} - Constructs a DeleteCommand with the specified task index.</li>
 *   <li>{@code execute()} - Executes the delete command, removing the task from the task list and returning the result.</li>
 * </ul>
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from the list. "
            + "Index of the task must be provided. Do check the list of tasks to get the index.\n"
            + "\tUsage: " + COMMAND_WORD + " <index>\n"
            + "\tExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Task deleted: %1$s";

    private final int toDelete;

    public DeleteCommand(int index) {
        this.toDelete = index;
    }

    /**
     * Executes the delete command which removes a task from the task list.
     * 
     * @return CommandResult indicating the success or failure of the command execution.
     *         On success, it returns a message indicating the task has been removed and the
     *         current number of tasks in the list. On failure, it returns an error message.
     */
    @Override
    public CommandResult execute() {
        try {
            Task deletedTask = tasksList.removeTask(toDelete);
            System.out.println("Got it. I've removed this task:\n" + deletedTask.toString());
            System.out.println("now you have " + tasksList.size() + " tasks in the list");
            return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
        } catch (Exception e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        }
    }
}
