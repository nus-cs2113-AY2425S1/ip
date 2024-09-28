package medea.command.create.task;

import medea.core.TaskList;

/**
 * Represents a command to create an event task.
 * This command stores the description of the task and the time range
 * during which the event occurs.
 */
public class EventCommand extends TaskCommand {

    /** The command word for the event command. */
    public static final String COMMAND_WORD = "event";

    /** The description of the event task. */
    private String description;

    /** The starting time of the event. */
    private String from;

    /** The ending time of the event. */
    private String to;

    /**
     * Constructs an EventCommand with the specified description and time range.
     *
     * @param description the description of the event
     * @param from the starting time of the event
     * @param to the ending time of the event
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds the event task to the provided TaskList.
     *
     * @param tasks the TaskList to which the event task will be added
     * @return a confirmation message indicating the task has been added
     */
    @Override
    protected String addTask(TaskList tasks) {
        return tasks.addEvent(description, from, to);
    }
}
