package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.Deadline;
import apsea.task.TaskList;
import apsea.ui.Ui;

/**
 * Represents a command to add a deadline to the task list.
 * <code>fullCommand</code> represents the user's input.
 */
public class AddDeadlineCommand extends Command {
    private String fullCommand;
    private final int NAME_POSITION = 9;
    private final String DEADLINE_FORMAT_ERROR = "\tSorry, please use the format:\n"
            + "\tdeadline [task name] /by [time]";


    public AddDeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Checks if user input is not empty and of the correct deadline format.
     *
     * @param line User's full input.
     * @param byPosition Index of "/by" in user's input.
     * @return True if input is valid, false otherwise.
     */
    public boolean isValidDeadline(String line, int byPosition) {
        return (byPosition > 9) && (byPosition + 4 < line.length());
    }

    /**
     * Adds new deadline to the list of tasks.
     *
     * @param taskList List of tasks.
     * @param ui Ui for displaying messages.
     * @throws ApseaException if user input is not a valid deadline.
     */
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
