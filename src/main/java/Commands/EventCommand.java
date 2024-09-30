package Commands;

import Tasks.Event;

/**
 * Represents a command to add an event to the task list.
 * The event includes a description and time details (from and to).
 * 
 * <p>Usage:</p>
 * <pre>
 * event <description> /from <time1> /to <time2>
 * </pre>
 * 
 * <p>Example:</p>
 * <pre>
 * event do assignment week 7 /from 2024-09-19 23:59 /to 2024-09-20 23:59
 * </pre>
 * 
 * <p>Constants:</p>
 * <ul>
 *   <li>{@code COMMAND_WORD} - The command word to trigger this command.</li>
 *   <li>{@code MESSAGE_USAGE} - The usage message for this command.</li>
 *   <li>{@code MESSAGE_SUCCESS} - The success message for this command.</li>
 * </ul>
 * 
 * <p>Fields:</p>
 * <ul>
 *   <li>{@code toAdd} - The event to be added to the task list.</li>
 * </ul>
 * 
 * <p>Constructor:</p>
 * <ul>
 *   <li>{@code EventCommand(String description, String from, String to)} - Constructs an EventCommand with the specified description and time details.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code execute()} - Executes the command to add the event to the task list and returns the result of the command execution.</li>
 * </ul>
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a event to the list. "
            + "Description and time details (from and to) must be added\n"
            + "\tUsage: " + COMMAND_WORD + " <description> /from <time1> /to <time2>\n"
            + "\tExample: " + COMMAND_WORD + " do assignment week 7 /from 2024-09-19 23:59 /to 2024-09-20 23:59";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";

    private final Event toAdd;

    public EventCommand(String description, String from, String to) {
        this.toAdd = new Event(description, from, to);
    }

    /**
     * Executes the command to add an event to the task list.
     * 
     * @return CommandResult indicating the success or failure of the command execution.
     *         On success, it returns a message indicating the event has been added and the
     *         current number of tasks in the list. On failure, it returns an error message.
     */
    @Override
    public CommandResult execute() {
        try {
            tasksList.addTask(toAdd);
            System.out.println("Got it. I've added this Event:\n" + tasksList.get(tasksList.size() - 1).toString());
            System.out.println("now you have " + tasksList.size() + " tasks in the list");
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        }
    }
}
