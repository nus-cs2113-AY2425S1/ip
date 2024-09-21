package erika.command.addcommand;

import erika.console.Console;
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
        tasks.add(newTodo);
        fileSystem.appendTaskToFile(newTodo);
    }


}
