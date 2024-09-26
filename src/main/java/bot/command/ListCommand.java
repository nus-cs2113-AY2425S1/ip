package bot.command;

import bot.Storage;
import bot.Ui;
import task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showMessage("Your task list is empty.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + "." + tasks.getTask(i));
            }
        }
    }
}