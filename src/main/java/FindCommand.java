import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            ui.showMessage("No tasks found with the keyword: " + keyword);
        } else {
            ui.showFoundTasks(foundTasks);
        }
    }
}
