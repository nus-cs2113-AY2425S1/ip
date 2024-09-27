package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;

import static doug.UI.DASHED_LINE;

/**
 * Represents the actions of the List command
 * Lists out all the tasks currently in the ArrayList of tasks
 */
public class ListCommand extends Command{

    public ListCommand() {

    }

    /**
     * Lists in order, all the existing tasks in the ArrayList of tasks
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.getCount() == 0) {
            System.out.println(DASHED_LINE + "Got nothing on your roster bud.");
            System.out.print(DASHED_LINE);
            return;
        }

        System.out.println(DASHED_LINE + "Here, let me lay out your tasks for you.");
        for (int i = 0; i < tasks.getCount(); i++) {
            System.out.println((i+1) + "." + tasks.getTask(i).toString());
        }
        System.out.print(DASHED_LINE);
    }
}
