package erika.command;

import erika.filesystem.FileSystem;
import erika.ui.Ui;
import erika.tasklist.TaskList;

public class ExitCommand extends Command{
    public void execute(TaskList tasks, Ui ui, FileSystem fileSystem) {

    }
    public boolean isExit() {
        return true;
    }
}
