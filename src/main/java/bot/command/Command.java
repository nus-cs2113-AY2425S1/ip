package bot.command;

import bot.Storage;
import bot.TobyBotException;
import bot.Ui;
import task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TobyBotException;
    public boolean isExit() {
        return false;
    }
}