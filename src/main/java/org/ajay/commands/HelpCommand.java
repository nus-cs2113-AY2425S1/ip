package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

/**
 * Prints help message.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = """
            Print help message.
            Example: """ + COMMAND_WORD;

    /**
     * Executes the help command.
     *
     * @param tasks   TaskList containing all tasks.
     * @param ui      TextUi object to interact with the user.
     * @param storage Storage object to save the task list.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.printHelpMsgs();
    }
}
