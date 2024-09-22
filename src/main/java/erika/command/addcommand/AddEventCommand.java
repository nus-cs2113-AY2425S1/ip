package erika.command.addcommand;
import erika.console.Console;
import erika.filesystem.FileSystem;
import erika.task.Event;
import erika.tasklist.TaskList;

import java.io.IOException;

public class AddEventCommand extends AddCommand {
    String from;
    String to;
    public AddEventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        fileSystem.appendTaskToFile(newEvent);
    }
}
