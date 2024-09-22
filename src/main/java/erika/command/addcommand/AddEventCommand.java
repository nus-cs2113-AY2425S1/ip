package erika.command.addcommand;
import erika.console.Console;
import erika.filesystem.FileSystem;
import erika.task.Event;
import erika.tasklist.TaskList;

import java.io.IOException;

public class AddEventCommand extends AddCommand {
    String from;
    String to;
    /**
     * @param description Textual description of the entry
     * @param from Textual value representing the start date/time of the event
     * @param to Textual value representing the end date/time of the event
     */
    public AddEventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Overrides the default Command method execute
     * Adds a new <code>Event</code> object to the <code>TaskList</code> list
     * Appends a new line to the text file in the host filesystem
     * @param tasks TaskList object representing the tasks stored
     * @param fileSystem FileSystem object used to interface with the file system of the host
     * @throws IOException when there is an error in accessing the text file
     */
    @Override
    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        fileSystem.appendTaskToFile(newEvent);
    }
}
