package Commands;

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

