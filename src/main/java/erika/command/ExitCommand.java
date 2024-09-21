package erika.command;

import erika.console.Console;
import erika.filesystem.FileSystem;
import erika.ui.Ui;
import erika.tasklist.TaskList;

public class ExitCommand extends Command{
    public void execute(TaskList tasks, FileSystem fileSystem) {
        Console.printGoodbyeMessage();
    }
    public boolean isExit() {
        return true;
    }
}
