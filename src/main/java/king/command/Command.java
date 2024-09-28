package king.command;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

import java.io.IOException;

public abstract class Command {

    /**
     * Executes the specific command.
     *
     * @param tasks   The task list that the command will operate on.
     * @param ui      The user interface that displays information to the user.
     * @param storage The storage system to read from or write to a file.
     * @throws KingException If there is an error specific to the task system.
     * @throws IOException   If there is an error related to file I/O.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KingException, IOException;
}
