package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.printTaskList(tasklist.getTasks());
    }
}
