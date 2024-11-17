package tars.command;

import tars.storage.Storage;
import tars.tarsexception.TarsException;
import tars.tasklist.TaskList;
import tars.task.Task;
import tars.userinterface.UserInterface;

import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws TarsException {
        List<Task> matchingTasks = tasks.findTasksContainingKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showNoMatchingTasksMessage();
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;  // find 命令不会退出程序
    }
}
