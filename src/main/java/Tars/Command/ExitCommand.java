package Tars.Command;

import Tars.TaskList;
import Tars.Storage;
import Tars.UserInterface;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;  // 确认该命令会退出程序
    }
}
