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
    /**
     * Overrides the default Command method execute
     * Adds a new <code>Todo</code> object to the <code>TaskList</code> list
     * Appends a new line to the text file in the host filesystem
     * @param tasks TaskList object representing the tasks stored
     * @param fileSystem FileSystem object used to interface with the file system of the host
     * @throws IOException when there is an error in accessing the text file
     */
    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException{
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        fileSystem.appendTaskToFile(newTodo);
    }


}
