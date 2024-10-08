package Commands;

import Tasks.Deadline;
import utils.DateTimeParser;


/**
 * Represents a command to add a deadline task to the task list.
 * The command word for this action is "deadline".
 * 
 * <p>Usage:</p>
 * <pre>
 * deadline <description> /by <time>
 * </pre>
 * 
 * <p>Example:</p>
 * <pre>
 * deadline do assignment week 7 /by 2021-03-19 23:59
 * </pre>
 * 
 * <p>Upon successful execution, the new deadline task will be added to the task list,
 * and a success message will be returned.</p>
 * 
 * <p>Attributes:</p>
 * <ul>
 *   <li>COMMAND_WORD: The command word for adding a deadline.</li>
 *   <li>MESSAGE_USAGE: The usage message for the deadline command.</li>
 *   <li>MESSAGE_SUCCESS: The success message template for adding a deadline.</li>
 * </ul>
 * 
 * <p>Constructor:</p>
 * <ul>
 *   <li>{@code DeadlineCommand(String description, String by)}: Creates a new DeadlineCommand with the specified description and time.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code execute()}: Executes the command to add the deadline task to the task list and returns the result of the execution.</li>
 * </ul>
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to the list. "
            + "Description and time detail must be added. "
            + "The time would be formatted if possible.\n"
            + "\tUsage: " + COMMAND_WORD + " <description> /by <time>\n"
            + "\tExample: " + COMMAND_WORD + " do assignment week 7 /by 2021-03-19 23:59";

    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";

    private final Deadline toAdd;

    public DeadlineCommand(String description, String by) {
        String formattedTime = DateTimeParser.parseDateTime(by);
        this.toAdd = new Deadline(description, formattedTime);
    }

    /**
     * Executes the DeadlineCommand by adding a new task to the tasks list.
     * Prints a confirmation message and the total number of tasks in the list.
     * 
     * @return CommandResult indicating the success or failure of the command execution.
     */
    @Override
    public CommandResult execute() {
        try {
            tasksList.addTask(toAdd);
            System.out.println("Got it. I've added this Deadline:\n" + tasksList.get(tasksList.size() - 1).toString());
            System.out.println("now you have " + tasksList.size() + " tasks in the list");
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        }
    }
}
