package doug.Commands;

import doug.TaskList;
import doug.tasks.Event;

/**
 * Represents the actions of the Event command
 * Adds a new Event task to the ArrayList of tasks
 */
public class AddEventCommand extends Command {

    /**
     * Creates a new Event object and adds it to the existing TaskList
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param eventName Name of the new event task to be added
     * @param eventFrom Start date/time of the new event task to be added
     * @param eventBy End date/time of the new event task to be added
     */
    public static void newEvent(TaskList tasks, String eventName, String eventFrom, String eventBy) {
        Event eventTask = new Event(eventName, eventFrom, eventBy);
        addNewTask(tasks, eventTask);
    }
}
