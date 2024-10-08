package Commands;

/**
 * Represents a command to list all tasks.
 * The command word for this command is "list".
 * 
 * <p>Usage:</p>
 * <pre>
 * list: Shows all tasks in the list.
 * Example: list
 * </pre>
 * 
 * <p>Upon execution, this command returns a CommandResult containing a message
 * with all tasks in the list formatted as a numbered list.</p>
 * 
 * <p>Example of the success message:</p>
 * <pre>
 * Here are the tasks in your list:
 *  1. Task 1
 *  2. Task 2
 *  ...
 * </pre>
 * 
 * @return CommandResult containing the success message with the list of tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all tasks in the list.\n"
            + "\tExample: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list:\n";

    /**
     * Executes the list command which formats and lists all tasks in the task list.
     *
     * @return CommandResult containing a success message and the formatted list of tasks.
     */
    @Override
    public CommandResult execute() {
        String[] inputed_tasks = new String[tasksList.size()];
        int maxLenght = tasksList.size()/10 + 3;
        for (int i = 0; i < tasksList.size(); i++) {
            inputed_tasks[i] = String.format("%" + maxLenght + "s", (i + 1) + ". ") + tasksList.get(i).toString();
        }
        return new CommandResult(MESSAGE_SUCCESS + String.join("\n", inputed_tasks));
    }
}