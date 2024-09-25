package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

public abstract class Command {

    protected boolean isExit = false;
    protected String instructions;

    public Command() {
        this.instructions = "";
    }

    public Command(String instruction) {
        this.instructions = instruction;
    }

    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws AlyException;

    protected void setExit() {
        this.isExit = true;
    }

    public boolean hasExited() {
        return isExit;
    }

}