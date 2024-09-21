package doug.Commands;

import doug.TaskList;
import doug.tasks.Todo;

/**
 * Represents the actions of the Todo command
 * Adds a new Todo task to the ArrayList of tasks
 */
public class AddToDoCommand extends Command {

    /**
     * Creates a new Todo object and adds it to the existing TaskList
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param todoName Name of the new todo task to be added
     */
    public static void newToDo(TaskList tasks, String todoName) {
        Todo todoTask = new Todo(todoName);
        addNewTask(tasks, todoTask);
    }
}
