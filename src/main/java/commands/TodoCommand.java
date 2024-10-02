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
        try {
            taskList.addTask(todo);
            ui.printAddMessage(taskList.get(taskList.size() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            ui.invalidTask(todo);
        }
    }
}
