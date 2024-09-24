package medea.command;

import medea.Storage;
import medea.TaskList;
import medea.Ui;

public class InvalidCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage ){
        ui.showMsg("Sorry. I don't recognize that command.");
    }
}
