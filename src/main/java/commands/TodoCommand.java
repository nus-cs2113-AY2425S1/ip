package commands;

import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

public class TodoCommand extends Command {
    private Todo todo;
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(todo);
        ui.printAddMessage(taskList.get(taskList.size() - 1));
    }
}
