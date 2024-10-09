package commands;

import java.io.IOException;

import storage.TaskEncoder;
import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

public class TodoCommand extends Command {
    private Todo todo;
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws IOException {
        taskList.addTask(todo);
        TaskEncoder.addTask(todo.toString());
        ui.printAddMessage(taskList.get(taskList.size() - 1));
    }
}
