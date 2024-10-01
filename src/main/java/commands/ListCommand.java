package commands;

import task.Task;

import java.util.ArrayList;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand instance.
     */
    public ListCommand() {
        this.isExit = false;
    }

    /**
     * Executes the command to list all tasks.
     *
     * @param tasks the list of tasks to be displayed
     * @return a formatted string containing all tasks in the list
     */
    @Override
    public String execute(ArrayList<Task> tasks) {
        StringBuilder string = new StringBuilder("\tHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            string.append("\t\t").append(i + 1).append(".").append(tasks.get(i));
            if (i != tasks.size() - 1) {
                string.append("\n");
            }
        }
        return string.toString();
    }

}
