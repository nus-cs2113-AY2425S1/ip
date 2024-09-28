package medea.command.createTask;

import medea.core.TaskList;

/**
 * Represents a command to create a to-do task.
 * This command stores the description of the to-do task.
 */
public class TodoCommand extends TaskCommand {

    /** The command word for the to-do command. */
    public static final String COMMAND_WORD = "todo";

    /** The description of the to-do task. */
    private String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description the description of the to-do task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds the to-do task to the provided TaskList.
     *
     * @param tasks the TaskList to which the to-do task will be added
     * @return a string representation of the created to-do task
     */
    @Override
    protected String addTask(TaskList tasks) {
        return tasks.addTodo(description);
    }
}
