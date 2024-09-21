package erika.command;

import erika.exception.EmptyListException;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.console.Console;
import erika.tasklist.TaskList;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListCommand extends Command{
    public void execute(TaskList tasks, FileSystem fileSystem) throws EmptyListException {
        if(TaskList.getTaskArraySize() < 1) {
            throw new EmptyListException();
        }
        Console.printMessage(printList(tasks));
    }
    public boolean isExit() {
        return false;
    }

    protected String printList(TaskList tasks){
        return "Here are the items in your list: \n\t" +
                IntStream.range(0,TaskList.getTaskArraySize())
                .mapToObj(i -> (i + 1) + ". " + tasks.getTask(i))
                .collect(Collectors.joining("\n\t"));
    }
}
