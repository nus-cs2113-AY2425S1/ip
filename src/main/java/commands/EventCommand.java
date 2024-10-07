package commands;

import tasks.Event;
import tasks.TaskList;
import ui.Ui;

import java.time.DateTimeException;


public class EventCommand extends Command {
    private Event event;
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(event);
        ui.printAddMessage(taskList.get(taskList.size() - 1));
    }
    public EventCommand(Event event) {
        this.event = event;
    }
}
