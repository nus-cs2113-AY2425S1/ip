package bot.command;

import bot.Storage;
import bot.Ui;
import task.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(index).markAsNotDone();
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.getTask(index));
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Invalid task number.");
        }
    }
}