package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.Event;
import apsea.task.TaskList;
import apsea.ui.Ui;

/**
 * Represents a command to add an event to the task list.
 * <code>fullCommand</code> represents the user's input.
 */
public class AddEventCommand extends Command {
    private String fullCommand;
    private final int NAME_POSITION = 6;
    private final String EVENT_FORMAT_ERROR = "\tSorry, Please use the format:\n" +
            "\tevent [task name] /from [time] /to [event] ";


    public AddEventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Checks if user input is not empty and of the correct event format.
     *
     * @param line User's full input.
     * @param fromPosition Index of "/from" in user's input.
     * @param toPosition Index of "to" in user's input.
     * @return true if input is valid, false otherwise.
     */
    public boolean isValidEvent(String line, int fromPosition, int toPosition) {
        //event name is valid when /from starts after index 6
        boolean hasFrom = fromPosition > 6;
        boolean hasTo = toPosition >= 0;
        boolean isValidFrom = fromPosition + 6 < toPosition;
        boolean isValidTo = line.length() > toPosition + 4;

        return hasFrom && hasTo && isValidFrom && isValidTo;
    }

    /**
     * Adds new event to the list of tasks.
     *
     * @param taskList List of tasks.
     * @param ui Ui for displaying messages.
     * @throws ApseaException if input is empty or of wrong format.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui) throws ApseaException {

        int fromPosition = fullCommand.indexOf("/from");
        int toPosition = fullCommand.indexOf("/to");

        if (!isValidEvent(fullCommand, fromPosition, toPosition)) {
            throw new ApseaException(EVENT_FORMAT_ERROR);
        }

        taskList.addTask(new Event(fullCommand.substring(NAME_POSITION, fromPosition),
                fullCommand.substring(fromPosition + 6, toPosition - 1),
                fullCommand.substring(toPosition + 4)));

        ui.printAddTask(taskList);
        ui.printTotalTaskCount(taskList);
    }
}
