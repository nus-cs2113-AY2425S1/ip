import java.util.List;
import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        List<Task> matchingTasks = new ArrayList<>();
        List<Task> allTasks = tasks.getTasks();

        // Find tasks that contain the keyword
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.show("No matching tasks found.");
        } else {
            ui.show("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.show((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}
