package luke.commands;

import luke.TaskList;
import luke.Ui;

public abstract class Command {
    public boolean isExit;

    public Command() {

    }

    public abstract void execute(TaskList taskList, Ui ui, String[] inputArr);
}
