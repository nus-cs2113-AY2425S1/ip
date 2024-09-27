package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;

import static doug.UI.DASHED_LINE;

/**
 * Represents the actions of the Find command
 * Tries to find and print all existing tasks that match the entered keyword
 */
public class FindCommand extends Command {

    private static String keyword;

    public FindCommand(String keyword) {
        FindCommand.keyword = keyword;
    }

    /**
     * Checks all tasks in current ArrayList to see if they contain the keyword
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        boolean found = false;
        System.out.println(DASHED_LINE);
        for (int i = 0; i < tasks.getCount(); i++) {
            if (tasks.getTask(i).getDescription().contains(keyword)) {
                System.out.println(tasks.getTask(i));
                found = true;
            }
        }
        if (found) {
            System.out.println("Here, found these tasks for ya!\n" + DASHED_LINE);
        } else {
            System.out.println("Doesn't ring a bell buddy...\n" + DASHED_LINE);
        }
    }
}
