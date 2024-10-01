package chattycharlie.commands;

import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;

public class MarkCommand implements Command {
    private int toMarkIndex;

    public MarkCommand(String line) {
        String toMarkIndex = line.substring(4).trim();
        this.toMarkIndex = Integer.parseInt(toMarkIndex) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(toMarkIndex);
        ui.displayTask(taskList.getTask(toMarkIndex));
    }
}
