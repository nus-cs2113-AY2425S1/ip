package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;
import codecatalyst.exception.InvalidCommandException;
import codecatalyst.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified search keyword.
     *
     * @param input The input string containing the keyword to search for.
     */
    public FindCommand(String input) {
        this.keyword = input;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasklist.getSize(); i ++) {
            Task task = tasklist.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.size() > 0) {
            ui.printMatchingTasks(matchingTasks, keyword);
        } else {
            ui.printNoMatchingTask();
        }
    }
}
