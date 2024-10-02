import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        // Store the keyword to search for
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Find tasks matching the keyword
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        // Display the matching tasks to the user
        ui.showMatchingTasks(matchingTasks);
    }
}
