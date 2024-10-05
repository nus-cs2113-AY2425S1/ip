package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.printHelpMsgs();
    }
}
