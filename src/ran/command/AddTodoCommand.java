package ran.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;
import ran.task.Todo;
import java.io.IOException;
import ran.exception.MissingArgumentException;

public class AddTodoCommand extends Command {
    public AddTodoCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws MissingArgumentException,
           IOException {
        if (commandArg.equals("")) {
            throw new MissingArgumentException(CommandType.TODO);
        }
        Todo newTodo = new Todo(commandArg);
        tasks.addTask(newTodo);
        int taskCount = tasks.getTaskCount();
        storage.addToDataFile(newTodo.dataFileInput());
        ui.printAddedTask(newTodo.toString(), taskCount);
        return false;
    }
}
