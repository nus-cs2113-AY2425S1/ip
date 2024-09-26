package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

public interface Command {
    boolean isExit();

    void execute(TaskList taskList, Ui ui, Storage storage) throws QuinnException, IOException;
}
