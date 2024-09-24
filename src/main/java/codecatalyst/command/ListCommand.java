package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;

import java.io.IOException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        ui.printTaskList(tasklist.getTasks());
    }
}
