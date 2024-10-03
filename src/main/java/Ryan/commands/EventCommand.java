package Ryan.commands;

import Ryan.exceptions.RyanException;
import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.tasks.*;

public class EventCommand extends Command {
    private final String command;

    public EventCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        String[] splitFrom = command.split("/from", 2);

        if (splitFrom.length < 2) {
            throw new RyanException("Event tasks should be in the format 'description /from start-time /to end-time'.");
        }

        String description = splitFrom[0].trim();
        String[] splitTo = splitFrom[1].split("/to", 2);

        if (splitTo.length < 2) {
            throw new RyanException("Event tasks should include both start-time and end-time.");
        }

        String from = splitTo[0].trim();
        String to = splitTo[1].trim();
        Task task = new Event(description, from, to);
        tasks.addTask(task);

        ui.showTaskAdded(task, tasks.size());
    }
}

