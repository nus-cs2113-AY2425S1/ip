package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        if (tasks.size() == 0) {
            ui.showMsg("The task list is empty.");
        } else {
            ui.showMsg("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMsg((i + 1) + ". " + tasks.getTask(i));
            }
        }
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
