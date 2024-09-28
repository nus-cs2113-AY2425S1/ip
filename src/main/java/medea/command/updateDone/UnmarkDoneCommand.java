package medea.command.updateDone;

/**
 * Represents a command to unmark a task as done.
 * This command is a specific implementation of the UpdateDoneCommand
 * that sets the task's status to not done.
 */
public class UnmarkDoneCommand extends UpdateDoneCommand {

    /** The command word for the unmark command. */
    public static final String COMMAND_WORD = "unmark";

    /**
     * Constructs an UnmarkDoneCommand with the specified task index.
     *
     * @param index the index of the task to unmark
     */
    public UnmarkDoneCommand(int index) {
        super(index);
    }

    /**
     * Indicates that the command is a Mark command.
     *
     * @return true to indicate task is now completed
     */
    @Override
    protected boolean isDone(){
        return false;
    };
}
