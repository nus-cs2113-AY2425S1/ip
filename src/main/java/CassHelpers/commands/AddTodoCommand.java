package CassHelpers.commands;

import CassHelpers.types.Todo;
import CassHelpers.util.Storage;
import CassHelpers.types.Task;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

/**
 * Command class responsible for adding a to-do task to the task list.
 * It parses the input string for the task name and creates a new to-do task.
 * The task is then added to the task list and saved to storage.
 */
public class AddTodoCommand implements Command {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private final String input;
    private final int TODO_OFFSET = 4;

    /**
     * Constructs a new AddTodoCommand.
     *
     * @param tasks The TaskList object where the new to-do task will be added.
     * @param input The user input containing the to-do details.
     */
    public AddTodoCommand(TaskList tasks, String input) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.input = input;
    }

    /**
     * Executes the command by parsing the input for a to-do task and adding it to the task list.
     */
    @Override
    public void execute() {
        String taskName = input.substring(TODO_OFFSET).trim();
        Todo newTodo = new Todo(taskName);
        taskList.add(newTodo);
        storage.appendTaskToFile(newTodo);
        System.out.println("Got it. I've added this task: \n " + newTodo.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
