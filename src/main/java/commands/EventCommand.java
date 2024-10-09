package commands;

import java.io.IOException;

import storage.TaskEncoder;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

/**
 * Class represents the command that adds an event task to the {@code TaskList}
 */
public class EventCommand extends Command {
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }
    /**
     * Executes adding an event to the TaskList parameter
     * @param taskList the TaskList object being executed on
     * @param ui the Ui object used for user interactions
     * @throws IOException when a file error occurs
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws IOException {
        taskList.addTask(event);
        TaskEncoder.addTask(event.toFileFormat());
        ui.printAddMessage(taskList.get(taskList.size() - 1));
    }
}
