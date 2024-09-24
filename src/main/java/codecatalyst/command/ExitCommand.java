package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
