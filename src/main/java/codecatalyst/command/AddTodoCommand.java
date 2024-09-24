package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;
import codecatalyst.exception.EmptyTaskDescriptionException;
import codecatalyst.task.Todo;

import java.io.IOException;

public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs an {@code AddTodoCommand} with the specified task description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }


    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws Exception {
        if (description.trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("Todo task description cannot be empty.");
        }
        Todo todo = new Todo(description);
        tasklist.addTask(todo);
        ui.printTaskAdded(todo, tasklist.getSize());
        storage.saveTasksToFile(tasklist.getTasks());
    }

}
