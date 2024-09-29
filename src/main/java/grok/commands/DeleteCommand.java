package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex-1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                ui.showError("Invalid task index. Please provide a valid index.");
                return;
            }

            ui.showLine();
            ui.showMsg("Noted. I've removed this task:");
            ui.showMsg(tasks.deleteTask(taskIndex).toString());  // Return and show the deleted task
            ui.showMsg("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            ui.showLine();

            storage.saveTasks(tasks.getTasks());  // Save after deletion
        } catch (Exception e) {
            ui.showError("Error deleting task: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
