package commands;

import tasks.Task;
import data.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public UnmarkCommand(String cmd, String args) {
        super(cmd, args);
    }

    /**
     * Unmark the task at the given index as done
     * @param tasks TaskList object that stores all the tasks currently stored
     * @param ui Ui object used for interacting with the user
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui) {
        Task toMark = tasks.getTask(Integer.parseInt(args) - 1);
        toMark.markUnDone();
        System.out.println(Ui.DIVIDER + "OK, I've marked this task as not done yet: "
                + toMark.getDescription()
                + "\n" + Ui.DIVIDER);
    }
}
