package Commands;

/**
 * Represents a command to mark a task as undone.
 * The command requires the index of the task to be provided.
 * 
 * <p>Usage: unmark <index></p>
 * <p>Example: unmark 1</p>
 * 
 * <p>Constants:</p>
 * <ul>
 *   <li>{@code COMMAND_WORD} - The command word "unmark".</li>
 *   <li>{@code MESSAGE_USAGE} - The usage message for the command.</li>
 *   <li>{@code MESSAGE_SUCCESS} - The success message template for the command.</li>
 * </ul>
 * 
 * <p>Fields:</p>
 * <ul>
 *   <li>{@code toUnmark} - The index of the task to be marked as undone.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code UnmarkCommand(int index)} - Constructs an UnmarkCommand with the specified task index.</li>
 *   <li>{@code execute()} - Executes the command to mark the task as undone and returns the result.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@code IndexOutOfBoundsException} - If the provided index is out of bounds of the task list.</li>
 * </ul>
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task as undone. "
            + "Index of the task must be provided. Do check the list of tasks to get the index.\n"
            + "\tUsage: " + COMMAND_WORD + " <index>\n"
            + "\tExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Task unmarked: %1$s";

    private final int toUnmark;

    public UnmarkCommand(int index) {
        this.toUnmark = index;
    }

    /**
     * Executes the unmark command which marks a task as undone in the task list.
     *
     * @return CommandResult containing the result message of the unmark operation.
     *         If the task is successfully marked as undone, the message will indicate success.
     *         If an IndexOutOfBoundsException occurs, the message will contain the error details.
     */
    @Override
    public CommandResult execute() {
        try{
            tasksList.get(this.toUnmark).markAsUndone();
            return new CommandResult("Nice! I've marked this task as undone:\n" + tasksList.get(this.toUnmark).toString());
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        } 
    }
}

