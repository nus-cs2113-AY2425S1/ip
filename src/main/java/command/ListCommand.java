package command;

import tasklist.TaskList;

public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     * Lists and displays all the tasks.
     *
     * @param taskList  The task list containing tasks.
     * @param userInput The user input containing the command description.
     */
    @Override
    public void execute(TaskList taskList, String userInput) {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.displayTasks());
        System.out.println(SEPARATOR);
    }
}
