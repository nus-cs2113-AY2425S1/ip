package nateh.commands;

import java.io.IOException;

import nateh.storage.TaskEncoder;
import nateh.tasks.TaskList;
import nateh.tasks.Todo;
import nateh.ui.Ui;

/**
 * Class represents the command that adds a todo task to the {@code TaskList}
 */
public class TodoCommand extends Command {
    private Todo todo;
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }
    /**
     * Executes adding a Todo to the TaskList parameter
     * @param taskList the TaskList object being executed on
     * @param ui the Ui object used for user interactions
     * @throws IOException when an input/output error occurs
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws IOException {
        taskList.addTask(todo);
        TaskEncoder.addTask(todo.toFileFormat());
        ui.printAddMessage(taskList.get(taskList.size() - 1));
    }
}
