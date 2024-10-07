package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}