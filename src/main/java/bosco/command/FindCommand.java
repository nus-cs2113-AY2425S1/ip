package bosco.command;

import bosco.task.Task;
import bosco.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
