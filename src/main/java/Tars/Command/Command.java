package Tars.Command;

import Tars.TaskList;
import Tars.Storage;
import Tars.UserInterface;
import Tars.TarsException;

public abstract class Command {
    public abstract void execute(TaskList tasks, UserInterface ui, Storage storage) throws TarsException;

    public boolean isExit() {
        return false;  // 默认情况下，命令不会退出程序
    }
}
