package command;

import datahandling.Storage;
import task.Task;
import task.TaskList;
import ui.UserInteraction;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) {
        List<Task> matchingTasks = tasks.getTasks().stream()
            .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
            .toList();

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks matching \"" + keyword + "\" found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}