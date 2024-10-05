package XiaoMe.commands;

import XiaoMe.TaskList;

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
    public String execute(TaskList tasks) {
        if (!tasks.isEmpty()) {
            StringBuilder string = new StringBuilder("\tHere are the tasks in your list:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                string.append("\t\t").append(i + 1).append(".").append(tasks.getTask(i));
                if (i != tasks.getSize() - 1) {
                    string.append("\n");
                }
            }
            return string.toString();

        } else {
            return """
                    \tYour list is currently empty.
                    \tFeel free to add some new tasks.""";
        }
    }

}
