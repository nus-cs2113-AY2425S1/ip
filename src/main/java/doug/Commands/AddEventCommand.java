package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;
import doug.tasks.Event;

/**
 * Represents the actions of the Event command
 * Adds a new Event task to the ArrayList of tasks
 */
public class AddEventCommand extends Command {

    private static String eventName;
    private static String eventFrom;
    private static String eventBy;

    public AddEventCommand(String eventName, String eventFrom, String eventBy) {
        AddEventCommand.eventName = eventName;
        AddEventCommand.eventFrom = eventFrom;
        AddEventCommand.eventBy = eventBy;
    }

    /**
     * Creates a new Event object and adds it to the existing TaskList
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Event eventTask = new Event(eventName, eventFrom, eventBy);
        addNewTask(tasks, eventTask, ui, storage);
    }
}
