package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = """
            Exits the program.
            Example: """ + COMMAND_WORD;

    /**
     * Executes the exit command.
     *
     * @param tasks   TaskList containing all tasks.
     * @param ui      TextUi object to interact with the user.
     * @param storage Storage object to save the task list.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        super.setExitBool(true);
        ui.printGoodbyeMsgs();
    }
}
