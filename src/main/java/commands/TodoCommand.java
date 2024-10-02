package commands;

import java.util.ArrayList;

import tasks.Task;
import tasks.Todo;
import ui.Skeleton;
import ui.Ui;

public class TodoCommand extends Command {
    private Todo todo;
    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        try {
            taskList.add(todo);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("added: " + taskList.get(taskList.size() - 1).getTask());
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            ui.invalidTask(todo);
        }
    }
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }
}
