package appal.commands;

import appal.exception.AppalException;
import appal.exception.InvalidSearchTargetException;
import appal.storage.Storage;
import appal.task.Task;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Messages.NO_SEARCH_RESULTS_MESSAGE;
import static appal.common.Messages.SEARCH_RESULTS_MESSAGE;
import static appal.common.Utils.COMMAND_FIND;

public class FindCommand extends Command {
    private String searchTarget;

    public FindCommand(String searchTarget) throws InvalidSearchTargetException {
        super(COMMAND_FIND);
        try {
            this.searchTarget = searchTarget.toLowerCase();
        } catch (NullPointerException e) {
            throw new InvalidSearchTargetException();
        }
    }

    public boolean containsSearchTarget(Task task) {
        return task.getTaskInfo().toLowerCase().contains(searchTarget);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        ui.printSeparator();
        int taskCount = 0;
        for (Task task : taskList.getTaskList()) {
            if (containsSearchTarget(task)) {
                taskCount += 1;
                System.out.print(taskCount + ".");
                ui.printOneTask(task);
            }
        }
        if (taskCount == 0) {
            ui.printMessage(NO_SEARCH_RESULTS_MESSAGE);
        } else {
            ui.printMessage(SEARCH_RESULTS_MESSAGE);
        }
    }
}
