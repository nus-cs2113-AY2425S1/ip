package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;

public class DeleteCommand implements Command {
    private int toDeleteIndex;

    public DeleteCommand(String line) throws CharlieExceptions {
        String toDeleteIndex = line.substring(7).trim();
        this.toDeleteIndex = Integer.parseInt(toDeleteIndex) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(toDeleteIndex);
    }
}
