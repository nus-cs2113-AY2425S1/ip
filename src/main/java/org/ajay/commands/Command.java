package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class Command {

    protected boolean isExit = false;

    public boolean getExitBool() {
        return isExit;
    }

    public void setExitBool(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
