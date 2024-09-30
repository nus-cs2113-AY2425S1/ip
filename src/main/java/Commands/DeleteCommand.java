package Commands;

import Tasks.Task;

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
