package command;

import data.Storage;
import task.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    /**
     * Save task list to storage for future sessions
     * @param tasks Task list to add the new task to
     * @param storage File to save and load the task list
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        storage.save(tasks);
        Ui.showEndChatMessage();
    }
}
