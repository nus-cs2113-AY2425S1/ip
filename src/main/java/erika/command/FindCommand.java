package erika.command;

import erika.exception.EmptyListException;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.task.Task;
import erika.tasklist.TaskList;

import java.io.IOException;
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
                IntStream.range(0,TaskList.getTaskArraySize())
                .filter(t -> tasks.getTask(t).getDescription().contains(this.key))
                .mapToObj(i -> (i + 1) + ". " + tasks.getTask(i))
                .collect(Collectors.joining("\n\t"));
    }

    public boolean isExit() {
        return false;
    }
}
