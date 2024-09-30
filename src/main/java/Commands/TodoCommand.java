package Commands;

import Tasks.Todo;

/**
 * Represents a command to add a Todo task.
 * This command adds a new Todo task to the task list.
 * 
 * <p>Usage:</p>
 * <pre>
 *     todo <description>
 * </pre>
 * 
 * <p>Example:</p>
 * <pre>
 *     todo do assignment week 7
 * </pre>
 * 
 * <p>Constants:</p>
 * <ul>
 *     <li>{@code COMMAND_WORD} - The command word to trigger this command ("todo").</li>
 *     <li>{@code MESSAGE_USAGE} - The usage message for this command.</li>
 *     <li>{@code MESSAGE_SUCCESS} - The success message template for this command.</li>
 * </ul>
 * 
 * <p>Fields:</p>
 * <ul>
 *     <li>{@code toAdd} - The Todo task to be added.</li>
 * </ul>
 * 
 * <p>Constructor:</p>
 * <ul>
 *     <li>{@code TodoCommand(String description)} - Constructs a new TodoCommand with the specified description.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *     <li>{@code execute()} - Executes the command to add the Todo task to the task list and returns the result.</li>
 * </ul>
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo to the list. "
            + "Description details must be added\n"
            + "\tUsage: " + COMMAND_WORD + " <description>\n"
            + "\tExample: " + COMMAND_WORD + " do assignment week 7";

    public static final String MESSAGE_SUCCESS = "New todo added: %1$s";

    private final Todo toAdd;

    public TodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    /**
     * Executes the todo command which adds a new task to the task list.
     * 
     * @return CommandResult indicating the success or failure of the command execution.
     *         On success, it returns a message indicating the task has been added.
     *         On failure, it returns an error message.
     */
    @Override
    public CommandResult execute() {
        try {
            tasksList.addTask(toAdd);
            System.out.println("Got it. I've added this Todo:\n" + tasksList.get(tasksList.size() - 1).toString());
            System.out.println("now you have " + tasksList.size() + " tasks in the list");
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        }
    }
}
