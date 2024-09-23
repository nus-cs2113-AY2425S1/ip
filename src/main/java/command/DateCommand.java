package command;

import data.Storage;
import exceptions.IrisException;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateCommand extends Command {
    private static final String INVALID_DATE_FORMAT = "Invalid Date Format: Use YYYY-MM-DD format";

    LocalDate date;

    public DateCommand(String description) throws IrisException {
        try {
            this.date = LocalDate.parse(description);
        } catch (DateTimeParseException e) {
            throw new IrisException(INVALID_DATE_FORMAT);
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Here are the tasks before " + date + ":");
        Ui.showDivider();
        tasks.stream()
                .filter(task -> isBefore(task, date))
                .forEach(System.out::println);

        System.out.println("Here are the tasks after " + date + ":");
        tasks.stream()
                .filter(task -> isAfter(task, date))
                .forEach(System.out::println);
    }

    private boolean isBefore(Task task, LocalDate date2) {
        if (task.dueDate == null) {
            return false;
        }
        return task.dueDate.isBefore(date2);
    }

    private boolean isAfter(Task task, LocalDate date2) {
        if (task.dueDate == null) {
            return false;
        }
        return task.dueDate.isAfter(date2);
    }
}
