package erika.command.addcommand;

import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.settings.Settings;
import erika.task.Deadline;
import erika.tasklist.TaskList;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.io.IOException;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends AddCommand {
    private String by;
    public AddDeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException, OutOfBoundsException {
        Deadline deadline = getDeadline();
        add(tasks, deadline);
        fileSystem.appendTaskToFile(deadline);
    }

    private Deadline getDeadline() {
        try{
            LocalDateTime datetime = LocalDateTime.parse(by, Settings.DATE_TIME_IN_FORMATTER);
            return new Deadline(description, datetime);
        } catch (DateTimeParseException e1) {
            try {
                return new Deadline(description, LocalDate.parse(by, Settings.DATE_IN_FORMATTER));
            } catch (DateTimeParseException e2) {
                return new Deadline(description, by);
            }
        }
    }
}
