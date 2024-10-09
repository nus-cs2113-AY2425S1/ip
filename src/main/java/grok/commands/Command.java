package grok.commands;

import grok.storage.Storage;
import grok.tasks.TaskList;
import grok.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
