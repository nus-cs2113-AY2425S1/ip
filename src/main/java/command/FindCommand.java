package command;

import data.Storage;
import exceptions.IrisException;
import task.TaskList;

/**
 * The FindCommand class handles the searching of tasks in the task list
 * based on a specified keyword or phrase. It retrieves and displays tasks
 * that match the given description.
 *
 * @author Tan Ping Hui
 */
public class FindCommand extends Command {

    private final String description;

    /**
     * Constructs a FindCommand object with the specified search description.
     *
     * @param description The keyword or phrase to search for in the task descriptions.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to find and display tasks that match the specified description.
     * It filters tasks from the task list based on whether their descriptions contain
     * the given keyword or phrase.
     *
     * @param tasks The task list to search for matching tasks.
     * @param storage The storage (not used in this command but included for method signature).
     * @throws IrisException If an error occurs during the search process.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws IrisException {
        System.out.println("Here are the matching tasks in your list:");
        tasks.stream()
                .filter(task -> task.description.contains(description))
                .forEach(System.out::println);
    }
}
