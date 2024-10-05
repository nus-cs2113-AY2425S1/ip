package commands;

import taskmanager.taskManager;
import tasks.Task;

/**
 * The FindCommand class handles searching for tasks that match a given keyword.
 * It iterates through the task list and prints tasks that contain the specified keyword in their description.
 */

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.substring(4).trim();
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword in their description.
     * It prints the matching tasks if any are found, or a message indicating that no matches were found.
     *
     * @param storage The storage object that manages the task list.
     */

    @Override
    public void execute(taskManager storage) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");

        int index;
        int count = 0;
        for (index = 0; index < storage.getTaskList().size(); index++) {
            Task task = storage.getTaskList().get(index);
            if (task.getContents().contains(keyword)) {
                storage.storagePrintTask(index + 1);
                count++;
            }
        }

        if (count == 0){
            System.out.println("No matching tasks found with keyword " + keyword);
        }
    }
}
