package main.java;

import java.util.Map;

/**
 * Represents a command to find tasks in the task list according to keyword(s).
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Performs the find command to search for tasks containing the specific keyword(s).
     *
     *
     * @param tasks The task list that the command will work on.
     * @param ui The UI component of the chat bot.
     * @param storage The storage component of the chat bot.
     * @throws KenChatException If an error occurs while performing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KenChatException {
        // Use the findTasksByKeywordWithIndex method that returns a Map of <index, task>
        Map<Integer, Task> matchingTasks = tasks.findTasksByKeywordWithIndex(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showError("No matching tasks found for: " + keyword);
        } else {
            ui.printLine();
            ui.showMessage("Here are the matching tasks in your list:");
            // Display the matching tasks with the correct indices
            for (Map.Entry<Integer, Task> entry : matchingTasks.entrySet()) {
                int index = entry.getKey(); // The original index of the task in the task list
                Task task = entry.getValue(); // The task itself
                ui.showMessage(index + ". " + task); // Output task with its original index
            }
            ui.printLine();
            System.out.println();
        }
    }
}
