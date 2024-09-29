package ran.command;

import main.java.Ui;
import main.java.TaskList;
import main.java.Storage;
import ran.exception.EmptyListException;

public class ShowListCommand extends Command {
    public ShowListCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws EmptyListException {
        int taskCount = tasks.getTaskCount(); 
        if (taskCount == 0) {
            throw new EmptyListException();
        }
        ui.printList(tasks.getAllTasks(), taskCount);
        return false;
    }
}
