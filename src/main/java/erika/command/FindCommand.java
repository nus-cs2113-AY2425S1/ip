package erika.command;

import erika.task.Task;
import erika.tasklist.TaskList;
import java.util.AbstractMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a specific "Find" command as interpreted from the user. A <code>FindCommand</code> object
 * corresponds to a user command to print all <code>Task</code> entries matching the supplied search key.
 */
public class FindCommand extends ListCommand {
    private String key;
    public FindCommand(String key) {
        super();
        this.key = key;
    }

    @Override
    protected String printList(TaskList tasks) {
        return "Here are the matching items in your list: \n\t"
                + IntStream.range(0, TaskList.getTaskArraySize())
                .mapToObj(i -> {
                    Task task = tasks.getTask(i);
                    return new AbstractMap.SimpleEntry<>(i, task);
                })
                .filter(entry -> entry.getValue().getDescription().contains(this.key))
                .map(entry -> (entry.getKey() + 1) + ". " + entry.getValue())
                .collect(Collectors.joining("\n\t"));
    }

    public boolean isExit() {
        return false;
    }
}
