package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ByeCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.sayGoodbye();
    }
    @Override
    public boolean isExit(){
        return true;
    }
}
