package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;
import chattycharlie.task.Event;
import chattycharlie.task.Task;

/**
 * Represents the command to be executed when the user inputs an event task.
 * This command creates an event task and adds it to the task list.
 */
public class EventCommand implements Command{
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an <code>EventCommand</code> from the provided user input line.
     * Parses the input to extract the task description, start time, and end time.
     *
     * @param line the input line containing the command and event task details.
     * @throws CharlieExceptions if the description or dates or times are missing/
     */
    public EventCommand(String line) throws CharlieExceptions {
        String[] eventParts = line.substring(5).trim().split("from");

        if (eventParts[0].isEmpty()) {
            throw CharlieExceptions.missingDescription(CommandType.EVENT);
        } else {
            description = eventParts[0].trim();
        }

        if (eventParts.length < 2) {
            throw CharlieExceptions.missingDates();
        }

        String[] eventTimes = eventParts[1].trim().split(" to ");

        if (eventTimes.length < 2) {
            throw CharlieExceptions.missingTimes();
        }

        from = eventTimes[0].trim();
        to = eventTimes[1].trim();
    }

    /**
     * Executes the <code>EventCommand</code> by adding the event task to the task list,
     * displaying a confirmation message to the user, and saving the updated task list.
     *
     * @param taskList the list of tasks to add the new event task to.
     * @param ui the user interface to display output to the user.
     * @param storage the storage system to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieExceptions {
        storage.saveTasks(taskList);
        Task eventTask = new Event(description, from, to);
        taskList.addTask(eventTask);
        ui.displayTaskAdded(eventTask);
    }
}
