package Commands;

/**
 * Represents a command to mark a task as done.
 * The command requires the index of the task to be marked.
 * 
 * <p>Usage: {@code mark <index>}</p>
 * <p>Example: {@code mark 1}</p>
 * 
 * <p>Fields:</p>
 * <ul>
 *   <li>{@code COMMAND_WORD} - The command word to trigger this command ("mark").</li>
 *   <li>{@code MESSAGE_USAGE} - The usage message for this command.</li>
 *   <li>{@code MESSAGE_SUCCESS} - The success message template for this command.</li>
 *   <li>{@code toMark} - The index of the task to be marked as done.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code MarkCommand(int index)} - Constructs a MarkCommand with the specified task index.</li>
 *   <li>{@code execute()} - Executes the command to mark the task as done and returns the result.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@code IndexOutOfBoundsException} - If the provided index is out of bounds of the task list.</li>
 * </ul>
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task as done. "
            + "Index of the task must be provided. Do check the list of tasks to get the index.\n"
            + "\tUsage: " + COMMAND_WORD + " <index>\n"
            + "\tExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Task marked: %1$s";

    private final int toMark;

    public MarkCommand(int index) {
        this.toMark = index;
    }

    /**
     * Executes the MarkCommand which marks a task as done in the tasks list.
     *
     * @return CommandResult indicating the outcome of the execution.
     *         If the task is successfully marked as done, a success message is returned.
     *         If an IndexOutOfBoundsException occurs, an error message is returned.
     */
    @Override
    public CommandResult execute() {
        try{
            tasksList.get(this.toMark).markAsDone();
            return new CommandResult("Nice! I've marked this task as done:\n" + tasksList.get(this.toMark).toString());
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        } 
    }
}

