package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.LukeException;

/**
 * Marks task as done.
 */
public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";

    /**
     * Marks the task specified by the user as done.
     * @param taskList Object of class TaskList that contains the user's list of tasks.
     * @param ui UI component for displaying messages.
     * @param inputs A string array of user input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.mark(inputs);
        } catch (LukeException e) {
            ui.printReply(e.getMessage());
        }
    }
}
