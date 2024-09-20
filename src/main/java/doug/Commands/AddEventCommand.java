package doug.Commands;

import doug.Main.DougException;
import doug.TaskList;
import doug.tasks.Event;

public class AddEventCommand extends Command {
    public static void newEvent(TaskList tasks, String eventName, String eventFrom, String eventBy) throws DougException {
        Event eventTask = new Event(eventName, eventFrom, eventBy);
        addNewTask(tasks, eventTask);
    }
}
