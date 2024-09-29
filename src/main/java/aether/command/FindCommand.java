package aether.command;

import aether.tasklist.TaskList;
import aether.ui.Ui;
import aether.storage.Storage;
import aether.DukeException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to find tasks containing a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();  // Case-insensitive search
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<String> matchingTasks = taskList.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .map(task -> task.toString())  // Convert each matching task to a string
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            ui.response("No matching tasks found.");
        } else {
            ui.response("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.response((i + 1) + ". " + matchingTasks.get(i));  // Display tasks
            }
        }
    }
}
