package bosco.command;

import bosco.task.Deadline;
import bosco.ui.Ui;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private final Deadline newDeadline;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.newDeadline = new Deadline(description, false, by);
    }

    @Override
    public void execute() {
        tasks.addTask(newDeadline);
        ui.printMessages(Ui.MESSAGE_ADDED_TASK, Ui.INDENT_EXTRA + newDeadline,
                ui.getTaskCountMessage(tasks.getSize()));
    }
}
