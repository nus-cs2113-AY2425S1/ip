package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.InsufficientArgumentsException;

/**
 * Adds an event.
 */
public class AddEventCommand extends Command{

    public static final String COMMAND_WORD = "event";

    /**
     * Executes the command by adding an event into the user's list of tasks.
     * @param taskList Object of class TaskList that contains the user's list of tasks.
     * @param ui UI component for displaying messages.
     * @param inputs A string array of user input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.addEvent(inputs);
        } catch (InsufficientArgumentsException e) {
            ui.printReply(e.getMessage());
        }

    }
}
