package aegis.command;

import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println();
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
