package command;

import data.Storage;
import task.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        storage.save(tasks);
        Ui.showEndChatMessage();
    }
}
