package CassHelpers.commands;

import CassHelpers.types.Todo;
import CassHelpers.util.Storage;
import CassHelpers.types.Task;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

public class AddTodoCommand implements Command {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private final String input;
    private final int todoOffset = 4;
    public AddTodoCommand(TaskList tasks, String input) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.input = input;
    }

    @Override
    public void execute() {
        String taskName = input.substring(todoOffset).trim();
        Todo newTodo = new Todo(taskName);
        taskList.add(newTodo);
        storage.appendTaskToFile(newTodo);
        System.out.println("Got it. I've added this task: \n " + newTodo.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
