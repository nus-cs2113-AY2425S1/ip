package bosco.command;

import bosco.task.TaskList;
import bosco.ui.Ui;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    protected TaskList tasks;
    protected Ui ui;
    protected int targetNumber = -1;

    /**
     * Constructs this class.
     */
    public Command() {
    }

    public Command(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    /**
     * Sets the TaskList and Ui objects to be used by this command.
     *
     * @param tasks <code>TaskList</code> to execute the commands on
     * @param ui <code>Ui</code> object to be used
     */
    public void setData(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void execute() {
    }
}
