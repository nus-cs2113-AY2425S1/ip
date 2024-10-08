package Commands;

import Tasks.Task;

/**
 * Represents a command to add a task to the task list.
 * The command word for this action is "task".
 * 
 * <p>Usage: task &lt;description&gt;</p>
 * <p>Example: task do assignment week 7</p>
 * 
 * <p>Upon execution, this command adds a new task with the specified description to the task list
 * and returns a success message.</p>
 * 
 * <p>If an error occurs during the execution, an error message is returned.</p>
 */
public class TaskCommand extends Command {
    public static final String COMMAND_WORD = "task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the list. "
            + "Description details must be added\n"
            + "\tUsage: " + COMMAND_WORD + " <description>\n"
            + "\tExample: " + COMMAND_WORD + " do assignment week 7";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private final Task toAdd;

    public TaskCommand(String description) {
        this.toAdd = new Task(description);
    }

    /**
     * Executes the task command by adding a task to the task list.
     * Prints confirmation messages to the console and returns a CommandResult.
     * 
     * @return CommandResult indicating the success or failure of the command execution.
     */
    @Override
    public CommandResult execute() {
        try {
            tasksList.addTask(toAdd);
            System.out.println("Got it. I've added this Task:\n" + tasksList.get(tasksList.size() - 1).toString());
            System.out.println("now you have " + tasksList.size() + " tasks in the list");
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        }
    }
}
