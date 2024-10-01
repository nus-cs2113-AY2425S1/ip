package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.Deadline;
import apsea.task.TaskList;
import apsea.ui.Ui;

public class AddDeadlineCommand extends Command {
    private String fullCommand;
    private final int NAME_POSITION = 9;
    private final String DEADLINE_FORMAT_ERROR = "\tSorry, please use the format:\n"
            + "\tdeadline [task name] /by [time]";


    public AddDeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public boolean isValidDeadline(String line, int byPosition) {
        return (byPosition > 9) && (byPosition + 4 < line.length());
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui) throws ApseaException {

        int byPosition = fullCommand.indexOf("/by");

        if (!isValidDeadline(fullCommand, byPosition)) {
            throw new ApseaException(DEADLINE_FORMAT_ERROR);
        }

        taskList.addTask(new Deadline(fullCommand.substring(NAME_POSITION, byPosition),
                fullCommand.substring(byPosition + 4)));

        ui.printAddTask(taskList);
        ui.printTotalTaskCount(taskList);
    }
}
