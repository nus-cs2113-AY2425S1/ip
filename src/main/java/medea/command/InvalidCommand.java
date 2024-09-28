package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

public class InvalidCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage ){
        ui.showMsg("Sorry. I don't recognize that command.");
    }
}
