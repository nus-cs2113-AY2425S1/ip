package jeremy.command;

import jeremy.exception.*;
import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws TaskNotFoundException, InvalidTaskNumberException,
                    EmptyArgumentException, InvalidCommandFormatException;

    public boolean isExit() {
        return false;
    }
}
