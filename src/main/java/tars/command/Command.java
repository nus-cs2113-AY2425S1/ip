package tars.command;

import tars.tasklist.TaskList;
import tars.storage.Storage;
import tars.userinterface.UserInterface;
import tars.tarsexception.TarsException;

public abstract class Command {
    public abstract void execute(TaskList tasks, UserInterface ui, Storage storage) throws TarsException;

    public boolean isExit() {
        return false;  // 默认情况下，命令不会退出程序
    }
}
