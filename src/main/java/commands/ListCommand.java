package commands;

import task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand() {
        this.isExit = false;
    }

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
