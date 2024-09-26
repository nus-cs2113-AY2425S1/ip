package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieExceptions;

    default boolean isExit() {
        return false;  // Default behavior: most commands are not exit commands
    }
}
