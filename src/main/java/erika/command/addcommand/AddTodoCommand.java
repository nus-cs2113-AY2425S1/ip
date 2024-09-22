package erika.command.addcommand;

import erika.filesystem.FileSystem;
import erika.task.Todo;
import erika.tasklist.TaskList;

import java.io.IOException;

public class AddTodoCommand extends AddCommand{
    public AddTodoCommand(String description) {
        super(description);
    }
    @Override
    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException{
        Todo newTodo = new Todo(description);
        add(tasks, newTodo);
        fileSystem.appendTaskToFile(newTodo);
    }
}
