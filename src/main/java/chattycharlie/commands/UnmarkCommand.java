package chattycharlie.commands;

import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;

public class UnmarkCommand implements Command {
    private int toUnmarkIndex;

    public UnmarkCommand(String line) {
        String toUnmarkIndex = line.substring(4).trim();
        this.toUnmarkIndex = Integer.parseInt(toUnmarkIndex) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(toUnmarkIndex);
        ui.displayTask(taskList.getTask(toUnmarkIndex));
    }
}
