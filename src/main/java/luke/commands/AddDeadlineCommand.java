package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.InsufficientArgumentsException;

/**
 * Adds a deadline.
 */
public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";

    /**
     * Executes the command by adding a deadline into the user's list of tasks.
     * @param taskList Object of class TaskList that contains the user's list of tasks.
     * @param ui UI component for displaying messages.
     * @param inputs A string array of user input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.addDeadline(inputs);
        } catch (Exception e) {
            ui.printReply(e.getMessage());
        }
    }
}
