package bot;

import task.TaskList;

abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TobyBotException;
    public boolean isExit() {
        return false;
    }
}