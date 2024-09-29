package luke.commands;

import luke.TaskList;
import luke.Ui;

/**
 * Lists all tasks.
 */
public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    /**
     * Lists all tasks currently in the user's list.
     * @param taskList Object of class TaskList that contains the user's list of tasks.
     * @param ui UI component for displaying messages.
     * @param inputs A string array of user input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        taskList.list();
    }
}
