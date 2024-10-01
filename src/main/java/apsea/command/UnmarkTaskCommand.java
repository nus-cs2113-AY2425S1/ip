package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.TaskList;
import apsea.ui.Ui;

public class UnmarkTaskCommand extends Command {
    private String[] words;
    private String UNMARK_TASK_ERROR_MESSAGE = "\tSorry, please use the format:\n"
            + "\tunmark [number]";

    public UnmarkTaskCommand(String[] words) {
        this.words = words;
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui) throws ApseaException {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            (taskList.getTask(taskIndex)).markAsUndone();

            ui.printUnmarkTask(taskList, taskIndex);
        } catch (Exception e) {
            throw new ApseaException(UNMARK_TASK_ERROR_MESSAGE);
        }
    }
}
