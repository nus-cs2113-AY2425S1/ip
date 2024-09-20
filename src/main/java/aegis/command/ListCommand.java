package aegis.command;

import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayTasks();
    }
}
