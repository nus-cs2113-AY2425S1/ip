package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;
import chattycharlie.task.Task;
import chattycharlie.task.Todo;

/**
 * Represents the command to add a to-do task to the task list.
 * This command creates a new to-do task and adds it to the list of tasks.
 */
public class TodoCommand implements Command{
    private String description;

    /**
     * Constructs a <code>TodoCommand</code> using the provided input line.
     * Parses the input to extract the description of the to-do task.
     *
     * @param line the input line containing the command and task description.
     * @throws CharlieExceptions if the to-do description is missing.
     */
    public TodoCommand(String line) throws CharlieExceptions {
        String text = line.substring(4).trim();
        if (text.isEmpty()) {
            throw CharlieExceptions.missingDescription(CommandType.TODO);
        } else {
            this.description = text;
        }
    }

    /**
     * Executes the <code>TodoCommand</code> by adding the to-do task to the task list,
     * displaying a confirmation message to the user, and saving the updated task list.
     *
     * @param taskList the list of tasks to add the new to-do task to.
     * @param ui the user interface for displaying output to the user.
     * @param storage the storage system to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        Task todoTask = new Todo(description);
        taskList.addTask(todoTask);
        ui.displayTaskAdded(todoTask);
    }
}
