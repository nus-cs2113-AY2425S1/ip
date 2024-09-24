package medea.command;

import medea.Storage;
import medea.TaskList;
import medea.Ui;

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

    public void execute(TaskList tasks, Ui ui, Storage storage){};
}
