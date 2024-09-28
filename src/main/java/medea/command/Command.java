package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

public abstract class Command {
    private int taskIndex = -1;

    public Command(){}

    public Command(int taskIndex){
        this.taskIndex = taskIndex;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean isExit(){
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
