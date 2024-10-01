package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.TaskList;
import apsea.ui.Ui;

public class MarkTaskCommand extends Command {
    private String[] words;
    private String MARK_TASK_ERROR_MESSAGE = "\tSorry, please use the format:\n"
            + "\tmark [number]";

    public MarkTaskCommand(String[] words) {
        this.words = words;
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui) throws ApseaException {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            (taskList.getTask(taskIndex)).markAsDone();

            ui.printMarkTask(taskList, taskIndex);
        } catch (Exception e) {
            throw new ApseaException(MARK_TASK_ERROR_MESSAGE);
        }
    }
}
