package erika.command.addcommand;

import erika.filesystem.FileSystem;
import erika.task.Todo;
import erika.tasklist.TaskList;
import java.io.IOException;

/**
 * Represents a user command to add an <code>Todo</code> object to the <code>TaskList</code> List.
 */
public class AddTodoCommand extends AddCommand {
    /**
     * Constructor for AddTodoCommand class.
     *
     * @param description Textual description of the entry
     */
    public AddTodoCommand(String description) {
        super(description);
    }

    /**
     * Overrides the default Command method execute.
     * Adds a new <code>Todo</code> object to the <code>TaskList</code> list.
     * Appends a new line to the text file in the host filesystem.
     *
     * @param tasks TaskList object representing the tasks stored.
     * @param fileSystem FileSystem object used to interface with the file system of the host.
     * @throws IOException when there is an error in accessing the text file.
     */
    @Override
    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException {
        Todo newTodo = new Todo(description);
        add(tasks, newTodo);
        fileSystem.appendTaskToFile(newTodo);
    }
}
