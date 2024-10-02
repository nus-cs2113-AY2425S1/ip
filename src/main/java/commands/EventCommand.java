package commands;

import tasks.Event;
import tasks.TaskList;
import ui.Ui;


public class EventCommand extends Command {
    private Event event;
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            taskList.addTask(event);
            ui.printAddMessage(taskList.get(taskList.size() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            ui.invalidTask(event);
        }
    }
    public EventCommand(Event event) {
        this.event = event;
    }
}
