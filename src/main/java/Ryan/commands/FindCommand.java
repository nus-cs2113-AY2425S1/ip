package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.tasks.*;
import Ryan.exceptions.RyanException;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String description;

    public FindCommand(String description){
        this.description = description;
    }

    public void execute(TaskList taskList, Ui ui) throws RyanException {
        if (description.trim().isEmpty()) {
            throw new RyanException("Find description cannot be empty.");
        }

        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showError("No tasks found with the given description.");
        } else {
            ui.showFoundTasks(matchingTasks);
        }
    }
}
