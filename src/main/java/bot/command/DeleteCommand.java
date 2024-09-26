package bot.command;

import bot.Storage;
import bot.TobyBotException;
import bot.Ui;
import task.TaskList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TobyBotException {
        try {
            ui.showMessage("Noted. I've removed this task:\n  " + tasks.removeTask(index));
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new TobyBotException("Invalid task number.");
        }
    }
}
