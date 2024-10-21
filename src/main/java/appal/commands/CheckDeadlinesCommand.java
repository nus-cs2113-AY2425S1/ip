package appal.commands;

import appal.exception.AppalException;
import appal.exception.InvalidDeadlineFormatException;
import appal.storage.Storage;
import appal.task.Deadline;
import appal.task.Task;
import appal.task.TaskList;
import appal.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static appal.common.Messages.CHECK_RESULTS_MESSAGE;
import static appal.common.Messages.NO_CHECK_RESULTS_MESSAGE;
import static appal.common.Utils.COMMAND_CHECK;

/**
 * CheckDeadlinesCommand checks the task list for deadlines that are due before or on a user specified date.
 */
public class CheckDeadlinesCommand extends Command {
    private LocalDate specifiedDate;

    /**
     * Class constructor.
     *
     * @param specifiedDeadline Specified date by user to check for deadlines.
     * @throws InvalidDeadlineFormatException if the user inputs an invalid date format.
     */
    public CheckDeadlinesCommand(String specifiedDeadline) throws InvalidDeadlineFormatException {
        super(COMMAND_CHECK);
        try {
            this.specifiedDate = LocalDate.parse(specifiedDeadline);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDeadlineFormatException();
        }
    }

    /**
     * Returns true if a task is a deadline that is due before or on the specified date.
     *
     * @param task The task to check.
     * @return True if is task is a deadline that is due before or on the specified date, false otherwise.
     */
    public boolean isBeforeOrOnDeadline(Task task) {
        return (task instanceof Deadline &&
                (((Deadline) task).getBy().isBefore(specifiedDate) || ((Deadline) task).getBy().equals(specifiedDate)));
    }

    /**
     * Executes the command to list out the deadlines that are due before or on the specified date.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     * @throws AppalException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        ui.printSeparator();
        int taskCount = 0;
        for (Task task : taskList.getTaskList()) {
            if (isBeforeOrOnDeadline(task)) {
                taskCount += 1;
                System.out.print(taskCount + ".");
                ui.printOneTask(task);
            }
        }
        if (taskCount == 0) {
            ui.printMessage(NO_CHECK_RESULTS_MESSAGE);
        } else {
            ui.printMessage(CHECK_RESULTS_MESSAGE);
        }
    }
}
