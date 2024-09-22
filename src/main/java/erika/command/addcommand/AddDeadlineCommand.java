package erika.command.addcommand;

import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.task.Deadline;
import erika.tasklist.TaskList;

import java.io.IOException;
/**
 * Represents a a user command to add a <code>Deadline</code> object to the <code>TaskList</code> List.
 */
public class AddDeadlineCommand extends AddCommand {
    private String by;
    /**
     * @param description Textual description of the entry
     * @param by Textual value representing the deadline
     */
    public AddDeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * Overrides the default Command method execute
     * Adds a new <code>Deadline</code> object to the <code>TaskList</code> list
     * Appends a new line to the text file in the host filesystem
     * @param tasks TaskList object representing the tasks stored
     * @param fileSystem FileSystem object used to interface with the file system of the host
     * @throws IOException when there is an error in accessing the text file
     */
    @Override
    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException, OutOfBoundsException {
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        fileSystem.appendTaskToFile(newDeadline);
    }
}
