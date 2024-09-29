package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showMsg("Bye. Hope to see you again soon! Keep Grokking :)");
        ui.showLine();
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Error saving tasks to file before exiting.");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
