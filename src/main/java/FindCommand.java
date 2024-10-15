package main.java;

import java.util.ArrayList;
import java.util.Map;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KenChatException {
        //ArrayList<Task> matchingTasks = tasks.findTasksByKeywordWithIndex(keyword);

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
