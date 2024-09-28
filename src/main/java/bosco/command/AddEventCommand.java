package bosco.command;

import bosco.task.Event;
import bosco.ui.Ui;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private final Event newEvent;

    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.newEvent = new Event(description, false, from, to);
    }

    @Override
    public void execute() {
        tasks.addTask(newEvent);
        ui.printMessages(Ui.MESSAGE_ADDED_TASK, Ui.INDENT_EXTRA + newEvent,
                ui.getTaskCountMessage(tasks.getSize()));
    }
}
