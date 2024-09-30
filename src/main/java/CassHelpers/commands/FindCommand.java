package CassHelpers.commands;

import CassHelpers.exceptions.NoTaskContainsMatchingPromptException;
import CassHelpers.types.Task;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

/**
 * Command class responsible for finding tasks that match a given prompt.
 * It searches the task list for tasks that contain the prompt and displays the results.
 */
public class FindCommand implements Command {
    private final ArrayList<Task> taskList;
    private final String input;
    private final int findOffset = 4;

    /**
     * Constructs a new FindCommand.
     *
     * @param tasks The TaskList object where tasks will be searched.
     * @param input The user input containing the prompt to search for.
     */
    public FindCommand(TaskList tasks, String input) {
        this.taskList = tasks.getTaskList();
        this.input = input;
    }

    /**
     * Executes the command by searching for tasks that match the given prompt.
     *
     * @throws NoTaskContainsMatchingPromptException If no tasks match the prompt or if the prompt is empty.
     */
    @Override
    public void execute() {
        String prompt = input.substring(findOffset).trim();

        if (prompt.isEmpty()) {
            throw new NoTaskContainsMatchingPromptException("Sorry! Please enter a prompt to be searched, use help to learn more");
        }

        String result = "Here are the matching tasks in your list: \n";
        int count = 0;

        for (Task task : taskList) {
            String taskDescription = task.getTaskName();
            if (taskDescription.contains(prompt)) {
                count += 1;
                result += count + ". " + task.toString() + " \n";
            }
        }

        if (count == 0) {
            throw new NoTaskContainsMatchingPromptException("Sorry! We couldn't find any task that contains " + prompt);
        }
        System.out.println(result);
    }
}
