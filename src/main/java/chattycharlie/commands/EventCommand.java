package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;
import chattycharlie.task.Event;
import chattycharlie.task.Task;

public class EventCommand implements Command{
    private String description;
    private String from;
    private String to;

    public EventCommand(String line) throws CharlieExceptions {
        String[] eventParts = line.substring(5).trim().split("from");

        if (eventParts[0].isEmpty()) { //no description
            throw CharlieExceptions.missingDescription(CommandType.EVENT);
        } else if (eventParts.length < 2) { //no from
            throw CharlieExceptions.missingTimes();
        } else {
            description = eventParts[0].trim();
        }

        String[] eventTimes = eventParts[1].trim().split(" to ");

        if (eventTimes.length < 2) {
            throw CharlieExceptions.missingTimes(); //change this to no end date
        }

        if (eventTimes[0].isEmpty() || eventTimes[1].isEmpty()) {
            throw CharlieExceptions.missingTimes();
        } else {
            from = eventTimes[0].trim();
            to = eventTimes[1].trim();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task eventTask = new Event(description, from, to);
        taskList.addTask(eventTask);
        ui.displayTaskAdded(eventTask);
    }
}
