package erika.command;

import erika.exception.EmptyListException;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.task.Task;
import erika.tasklist.TaskList;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindCommand extends ListCommand{
    private String key;
    public FindCommand(String key) {
        super();
        this.key = key;
    }

    @Override
    protected String printList(TaskList tasks){
        return "Here are the matching items in your list: \n\t" +
                IntStream.range(0, TaskList.getTaskArraySize())
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
