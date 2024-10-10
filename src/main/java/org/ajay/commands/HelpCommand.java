package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = """
                                               Print help message.
                                               Example: """ + COMMAND_WORD;

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.printHelpMsgs();
    }
}
