package medea.command.updateDone;

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

    @Override
    protected boolean isDone(){
        return true;
    };
}
