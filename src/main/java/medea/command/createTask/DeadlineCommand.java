package medea.command.createTask;

import medea.core.TaskList;

/**
 * Represents a command to create a deadline task.
 * This command stores the description of the task and the deadline by which
 * the task should be completed.
 */
public class DeadlineCommand extends TaskCommand {

    /** The command word for the deadline command. */
    public static final String COMMAND_WORD = "deadline";

    /** The description of the deadline task. */
    private String description;

    /** The deadline by which the task should be completed. */
    private String by;

    /**
     * Constructs a DeadlineCommand with the specified description and deadline.
     *
     * @param description the description of the task
     * @param by the deadline for the task
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds the deadline task to the provided TaskList.
     *
     * @param tasks the TaskList to which the deadline task will be added
     * @return a confirmation message indicating the task has been added
     */
    @Override
    public String addTask(TaskList tasks) {
        return tasks.addDeadline(description, by);
    }
}
