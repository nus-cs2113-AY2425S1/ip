package bosco.command;

import bosco.task.Task;
import bosco.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the command to find all tasks matching the input keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Class constructor.
     *
     * @param keyword Keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Overrides the default execute method to find, format and print
     * all tasks matching the keyword in the task list.
     * If no tasks are found, it prints message indicating no matching task.
     */
    @Override
    public void execute() {
        ArrayList<Task> filteredTasks = tasks.getAllTasks().stream()
                .filter((t) -> t.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredTasks.isEmpty()) {
            ui.printMessages(Ui.MESSAGE_NO_MATCH);
            return;
        }
        printFilteredTasks(filteredTasks);
    }

    private void printFilteredTasks(ArrayList<Task> filteredTasks) {
        System.out.println(Ui.DIVIDER);
        System.out.println(Ui.INDENT_START + Ui.MESSAGE_MATCH);
        for (Task task: filteredTasks) {
            System.out.println(Ui.INDENT_START + (tasks.getAllTasks().indexOf(task) + 1) + "." + task);
        }
        System.out.println(Ui.DIVIDER);
    }
}
