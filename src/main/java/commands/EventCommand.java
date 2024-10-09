package commands;

import java.io.IOException;

import storage.TaskEncoder;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

public class EventCommand extends Command {
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws IOException {
        taskList.addTask(event);
        TaskEncoder.addTask(event.toString());
        ui.printAddMessage(taskList.get(taskList.size() - 1));
    }
}
