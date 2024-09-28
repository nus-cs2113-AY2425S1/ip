package jeremy.command;

import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
