package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.Task;
import apsea.task.TaskList;
import apsea.ui.Ui;

public class DeleteTaskCommand extends Command {
    private String[] words;
    private String DELETE_FORMAT_ERROR = "\tSorry, please use the format:\n"
            + "\tdelete [number]";

    public DeleteTaskCommand(String[] words) {
        this.words = words;
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui) throws ApseaException {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            Task deletedTask = taskList.getTask(taskIndex);
            taskList.removeTask(taskIndex);

            ui.printDeleteTask(deletedTask);
            ui.printTotalTaskCount(taskList);
        } catch (Exception e) {
            throw new ApseaException(DELETE_FORMAT_ERROR);
        }
    }
}
