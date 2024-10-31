package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.IncorrectInputException;
import luke.exceptions.InsufficientArgumentsException;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";

    /**
     * Executes the command by deleting a task from the user's list of tasks.
     * @param taskList Object of class TaskList that contains the user's list of tasks.
     * @param ui UI component for displaying messages.
     * @param inputs A string array of user input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.deleteTask(inputs);
        } catch (InsufficientArgumentsException | IncorrectInputException e) {
            ui.printReply(e.getMessage());
        }
    }
}
