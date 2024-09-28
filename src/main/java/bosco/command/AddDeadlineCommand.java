package bosco.command;

import bosco.task.Deadline;
import bosco.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents the command to add a new Deadline.
 */
public class AddDeadlineCommand extends Command {
    private final Deadline newDeadline;

    /**
     * Class constructor.
     *
     * @param description Description of the new Deadline.
     * @param by Due time of the new Deadline.
     */
    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.newDeadline = new Deadline(description, false, by);
    }

    /**
     * Overrides the default execute method to add the new Deadline.
     * Prints message for the newly added Deadline.
     */
    @Override
    public void execute() {
        tasks.addTask(newDeadline);
        ui.printMessages(Ui.MESSAGE_ADDED_TASK, Ui.INDENT_EXTRA + newDeadline,
                ui.getTaskCountMessage(tasks.getSize()));
    }
}
