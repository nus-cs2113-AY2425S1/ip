package erika.command;

import erika.filesystem.FileSystem;
import erika.ui.Ui;
import erika.tasklist.TaskList;

abstract public class Command {
    abstract public void execute(TaskList tasks, Ui ui, FileSystem fileSystem);
    abstract public boolean isExit();
}
