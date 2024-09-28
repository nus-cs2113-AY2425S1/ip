package bosco.command;

import bosco.task.Event;
import bosco.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents the command to add a new Event.
 */
public class AddEventCommand extends Command {
    private final Event newEvent;

    /**
     * Class constructor.
     *
     * @param description Description of the new Event.
     * @param from Start time of the new Event.
     * @param to End time of the new Event.
     */
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.newEvent = new Event(description, false, from, to);
    }

    /**
     * Overrides the default execute method to add the new Event.
     * Prints message for the newly added Event.
     */
    @Override
    public void execute() {
        tasks.addTask(newEvent);
        ui.printMessages(Ui.MESSAGE_ADDED_TASK, Ui.INDENT_EXTRA + newEvent,
                ui.getTaskCountMessage(tasks.getSize()));
    }
}
