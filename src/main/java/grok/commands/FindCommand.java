package grok.commands;

import grok.tasks.Task;
import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

import java.util.List;

/**
 * Represents a command to find and display tasks that match a specified entry.
 * This command allows the user to search for tasks based on a keyword or phrase.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that match the keyword in their description.
     * Displays the matching tasks or a message if no matching tasks are found.
     *
     * @param tasks the TaskList to search through
     * @param ui the user interface for displaying messages
     * @param storage the Storage used to handle task persistence (not used in this command).
     */
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

    /**
     * Indicates that this command does not terminate the program.
     * @return false as the find command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
