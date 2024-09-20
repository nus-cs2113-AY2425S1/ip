package erika.command;

import erika.console.Console;
import erika.exception.EmptyDescriptionException;
import erika.exception.FormatErrorException;
import erika.filesystem.FileSystem;
import erika.settings.Settings;
import erika.task.Deadline;
import erika.task.Event;
import erika.task.Todo;
import erika.ui.Ui;
import erika.tasklist.TaskList;

import java.io.IOException;

public class AddCommand extends Command {
    private String description;
    private String by;
    private String from;
    private String to;

    public AddCommand(String description, String by, String from, String to) {
        super();
        this.description = description;
        this.by = by;
        this.from = from;
        this.to = to;
    }

    public void execute(TaskList tasks, Ui ui, FileSystem fileSystem) {
        try {
            addNewEvent(tasks);
        } catch (IOException e) {
            Console.printMessage("Unable to append Event to FS. IO Error occurred");
        }
    }

    public void addNewEvent(TaskList tasks) throws IOException {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
    }
    public boolean isExit() {
        return false;
    }
}
