package medea.command.update.done;

/**
 * Represents a command to mark a task as done.
 * This command is a specific implementation of the UpdateDoneCommand
 * that sets the task's status to done.
 */
public class MarkDoneCommand extends UpdateDoneCommand {

    /** The command word for the mark command. */
    public static final String COMMAND_WORD = "mark";

    /**
     * Constructs an MarkDoneCommand with the specified task index.
     *
     * @param index the index of the task to update
     */
    public MarkDoneCommand(int index) {
        super(index);
    }

    /**
     * Indicates that the command is an Unmark command.
     *
     * @return false to indicate task is no longer completed
     */
    @Override
    protected boolean isDone(){
        return true;
    };
}
