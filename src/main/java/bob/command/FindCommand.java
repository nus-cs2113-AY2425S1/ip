package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;
import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private final String keywords;

    public FindCommand(String keywords) {
        this.keywords = keywords.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTaskList();

        List<Integer> matchingIndices = taskList.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keywords))
                .map(taskList::indexOf)
                .distinct()
                .collect(Collectors.toList());

        if (matchingIndices.isEmpty()) {
            ui.printNoMatchingTasks();
        } else {
            ui.showMatchingTasks(taskList, matchingIndices);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
