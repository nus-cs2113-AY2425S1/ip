package apsea.command;

import apsea.task.TaskList;
import apsea.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
    }
    @Override
    public void runCommand(TaskList taskList, Ui ui) {
        this.isExit = true;
        ui.printBye();
    }


}
