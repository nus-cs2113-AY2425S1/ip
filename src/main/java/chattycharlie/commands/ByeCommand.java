package chattycharlie.commands;

import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.StringDesign;
import chattycharlie.userinteractions.Ui;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        System.out.println(StringDesign.CHARLIE + StringDesign.FAREWELL);
    }

    @Override
    public boolean isExit() {
        return true;  // This command causes the loop to exit
    }
}
