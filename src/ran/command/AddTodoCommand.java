package ran.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;
import ran.task.Todo;
import java.io.IOException;
import ran.exception.MissingArgumentException;

/**
 * Represents a user-prompted command to add a todo task. A <code>AddTodoCommand</code> object 
 * corresponds to a command represented with its argument stored as one string, which is the description 
 * of the todo task being added.
 * Inherits from base <code>Command</code> class.
 */
public class AddTodoCommand extends Command {
    /**
     * Constructor of a <code>AddTodoCommand</code> object.
     *
     * @param commandArg String representing description of the todo task
     */
    public AddTodoCommand(String commandArg) {
        super(commandArg);
    }

    /**
     * Executes the command to add a todo task to a <code>TaskList</code>, 
     * display a message of what has been added, 
     * and update the data file accordingly to be in sync.
     *
     * @param tasks TaskList object representing a list of tasks to be acted upon
     * @param ui Ui object representing the user interface to display the message
     * @param storage Storage object representing the data file to be modified
     * @return Boolean value of false as terminating condition is not met
     * @throws IOException If encounter error interfacing
     * @throws MissingArgumentException If command argument is an empty string
     */
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
