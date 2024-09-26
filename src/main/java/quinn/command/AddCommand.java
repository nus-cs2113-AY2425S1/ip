package quinn.command;

import quinn.storage.Storage;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

public abstract class AddCommand implements Command {
    private final String taskDescription;

    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
