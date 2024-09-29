package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex-1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(taskIndex).markAsDone();
            ui.showLine();
            ui.showMsg("Nice! I've marked this task as done:");
            ui.showMsg(tasks.getTask(taskIndex).toString());
            ui.showLine();
            storage.saveTasks(tasks.getTasks());  // Save after marking the task
        } catch (Exception e) {
            ui.showError("Error marking task.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
