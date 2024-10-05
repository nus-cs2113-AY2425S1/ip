package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = """
                                               Exits the program.
                                               Example: """ + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Exiting program...";

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        super.setExitBool(true);
        ui.printGoodbyeMsgs();
    }

}
