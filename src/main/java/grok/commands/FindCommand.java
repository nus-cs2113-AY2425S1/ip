package grok.commands;

import grok.tasks.Task;
import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showMsg("Here are the matching tasks in your list:");

        List<Task> matchingTasks = tasks.findTasks(keyword);
        for (int i = 0; i < matchingTasks.size(); i++) {
            ui.showMsg((i + 1) + ". " + matchingTasks.get(i));
        }

        if (matchingTasks.size() == 0) {
            ui.showMsg("No matching tasks found.");
        }

        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
