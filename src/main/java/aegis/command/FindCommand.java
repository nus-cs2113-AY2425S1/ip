package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;
import aegis.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AegisException {
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showError("No matching tasks found.");
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
