package Commands;

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

