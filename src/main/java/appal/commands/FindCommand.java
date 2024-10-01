package appal.commands;

import appal.storage.Storage;
import appal.task.Task;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Messages.NO_SEARCH_RESULTS_MESSAGE;
import static appal.common.Messages.SEARCH_RESULTS_MESSAGE;
import static appal.common.Utils.COMMAND_FIND;

/**
 * FindCommand class finds tasks that contains the string the user has inputted.
 */
public class FindCommand extends Command {
    private String searchTarget;

    /**
     * Class constructor.
     *
     * @param searchTarget The string used to find for tasks that contains it.
     */
    public FindCommand(String searchTarget) {
        super(COMMAND_FIND);
        this.searchTarget = searchTarget.toLowerCase();
    }

    /**
     * Returns true if and only if the task description contains the specified search target string.
     *
     * @param task The task to check its description for the search target.
     * @return True if the task description contains the search target, false otherwise.
     */
    public boolean containsSearchTarget(Task task) {
        return task.getTaskInfo().toLowerCase().contains(searchTarget);
    }

    /**
     * Executes the command to find tasks that contain a specified search target string from the task list.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
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
