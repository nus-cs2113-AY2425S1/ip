package commands;

import data.TaskList;
import ui.Ui;

public abstract class Command {
    protected String cmd;
    protected String args;
    public static final String COMMAND_WORD = "";
    protected boolean isExit = false;

    public Command(String cmd, String args) {
        this.cmd = cmd;
        this.args = args;
    }

    public boolean isExit() {
        return false;
    }

    public abstract void executeCommand(TaskList tasks, Ui ui);
}
