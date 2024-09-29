package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    // Updated constructor to take int instead of parsing string here
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex-1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            ui.showError("Invalid task number.");
            return;
        }

        // Unmark the task
        tasks.getTask(taskIndex).markAsNotDone();
        ui.showLine();
        ui.showMsg("OK, I've marked this task as not done yet:");
        ui.showMsg(tasks.getTask(taskIndex).toString());
        ui.showLine();

        // Save tasks after unmarking
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Error saving tasks to file.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
