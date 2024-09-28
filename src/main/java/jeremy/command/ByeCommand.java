package jeremy.command;

import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
