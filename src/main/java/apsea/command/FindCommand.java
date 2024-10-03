package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.Task;
import apsea.task.TaskList;
import apsea.ui.Ui;

public class FindCommand extends Command {
    private String fullCommand;
    private final int SEARCH_POSITION = 5;
    private final String FIND_FORMAT_ERROR = "\tSorry, please use the format:\n"
            + "\tfind [query]";


    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui) throws ApseaException {
        if (fullCommand.length() <= SEARCH_POSITION) {
            throw new ApseaException(FIND_FORMAT_ERROR);
        }
        String query = fullCommand.substring(SEARCH_POSITION);

        for (Task task: taskList.getTaskList()) {
            String lowerCaseQuery = query.toLowerCase();
            String lowerCaseDescription = (task.getDescription()).toLowerCase();
            if (lowerCaseDescription.contains(lowerCaseQuery)) {
                int taskNumber = taskList.getTaskIndex(task) + 1;
                ui.printFindMatch(task, taskNumber);
            }
        }
    }
}
