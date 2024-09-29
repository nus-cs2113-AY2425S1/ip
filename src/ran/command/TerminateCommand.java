package ran.command;

import main.java.Ui;
import main.java.TaskList;
import main.java.Storage;

public class TerminateCommand extends Command {
    public TerminateCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        return true;
    }
}
