package nell.command;

import nell.list.TaskList;
import nell.tasks.ToDo;

/**
 * Represents an executable "todo" command
 */
public class ToDoCommand extends Command{
    public static final String COMMAND_WORD = "todo";

    private String description;

    /**
     * Constructs a new ToDoCommand object with a specified task list and description
     *
     * @param tasks The specified task list
     * @param description The specified task description
     */
    public ToDoCommand(TaskList tasks, String description) {
        super(tasks);
        this.description = description.trim();
    }

    /**
     * Executes the command, by adding a new ToDo event to the task list
     */
    @Override
    public void execute() {
        ToDo toDoToAdd = new ToDo(description);
        tasks.addTask(toDoToAdd);
    }
}
