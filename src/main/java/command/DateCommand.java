package command;

import data.Storage;
import exceptions.IrisException;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The DateCommand class handles the filtering of tasks based on a specified date.
 * It retrieves and displays tasks that are either before or after the given date.
 * <p>
 * The date must be provided in the format YYYY-MM-DD.
 *
 * @author Tan Ping Hui
 */
public class DateCommand extends Command {
    private static final String INVALID_DATE_FORMAT = "Invalid Date Format (Use YYYY-MM-DD)";

    private final LocalDate date;

    /**
     * Constructs a DateCommand object and parses the given date string.
     *
     * @param description The date string to be parsed into a LocalDate.
     * @throws IrisException If the date format is invalid.
     */
    public DateCommand(String description) throws IrisException {
        try {
            this.date = LocalDate.parse(description);
        } catch (DateTimeParseException e) {
            throw new IrisException(INVALID_DATE_FORMAT);
        }
    }

    /**
     * Executes the command to display tasks that are either before or after the specified date.
     * It filters tasks from the task list and prints them to the console.
     *
     * @param tasks The task list from which to retrieve tasks.
     * @param storage The storage (not used in this command but included for method signature).
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Here are the tasks before " + date + ":");
        tasks.stream()
                .filter(task -> isBefore(task, date))
                .forEach(System.out::println);
        Ui.showDivider();

        System.out.println("Here are the tasks after " + date + ":");
        tasks.stream()
                .filter(task -> isAfter(task, date))
                .forEach(System.out::println);
    }

    /**
     * Checks if the specified task is due before the given date.
     *
     * @param task The task to check.
     * @param dateToCompare The date to compare against.
     * @return true if the task's due date is before the given date, false otherwise.
     */
    private boolean isBefore(Task task, LocalDate dateToCompare) {
        if (task.dueDate == null) {
            return false;
        }
        return task.dueDate.isBefore(dateToCompare);
    }

    /**
     * Checks if the specified task is due after the given date.
     *
     * @param task The task to check.
     * @param dateToCompare The date to compare against.
     * @return true if the task's due date is after the given date, false otherwise.
     */
    private boolean isAfter(Task task, LocalDate dateToCompare) {
        if (task.dueDate == null) {
            return false;
        }
        return task.dueDate.isAfter(dateToCompare);
    }
}
