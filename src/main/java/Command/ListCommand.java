package Command;

import TaskList.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, String userInput) {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.displayTasks());
        System.out.println(SEPARATOR);
    }
}
