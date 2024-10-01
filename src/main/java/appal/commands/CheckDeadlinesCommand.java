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

public class CheckDeadlinesCommand extends Command {
    private LocalDate specifiedDate;

    public CheckDeadlinesCommand(String specifiedDeadline) throws InvalidDeadlineFormatException {
        super(COMMAND_CHECK);
        try {
            this.specifiedDate = LocalDate.parse(specifiedDeadline);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDeadlineFormatException();
        }
    }

    public boolean isBeforeOrOnDeadline(Task task) {
        return (task instanceof Deadline &&
                (((Deadline) task).getBy().isBefore(specifiedDate) || ((Deadline) task).getBy().equals(specifiedDate)));
    }

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
