package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;
import doug.tasks.Todo;

/**
 * Represents the actions of the Todo command
 * Adds a new Todo task to the ArrayList of tasks
 */
public class AddToDoCommand extends Command {

    private static String todoName;

    public AddToDoCommand(String todoName) {
        AddToDoCommand.todoName = todoName;
    }

    /**
     * Creates a new Todo object and adds it to the existing TaskList
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Todo todoTask = new Todo(todoName);
        addNewTask(tasks, todoTask, ui, storage);
    }
}
