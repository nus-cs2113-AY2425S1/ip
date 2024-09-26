package bosco.command;

import bosco.task.TaskList;
import bosco.ui.Ui;

public abstract class Command {
    protected TaskList tasks;
    protected Ui ui;
    protected int targetNumber = -1;

    public Command() {
    }

    public Command(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public void setData(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void execute() {
    }
}
