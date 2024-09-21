package erika.command.addcommand;

import erika.console.Console;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.task.Deadline;
import erika.tasklist.TaskList;

import java.io.IOException;

public class AddDeadlineCommand extends AddCommand {
    private String by;
    public AddDeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException, OutOfBoundsException {
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        fileSystem.appendTaskToFile(newDeadline);
    }
}
