package doug.Commands;

import doug.Main.DougException;
import doug.Storage;
import doug.TaskList;
import doug.tasks.Todo;

import java.io.IOException;

public class AddToDoCommand extends Command {
    public static void newToDo(TaskList tasks, String todoName) throws DougException {
        Todo todoTask = new Todo(todoName);
        addNewTask(tasks, todoTask);
    }
}
