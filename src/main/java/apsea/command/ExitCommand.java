package apsea.command;

import apsea.storage.Storage;
import apsea.task.TaskList;
import apsea.ui.Ui;

/**
 * Represents a command to exit Apsea and stop taking user inputs.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        this.isExit = true;

        storage.saveData(taskList);
        ui.printBye();
    }


}
