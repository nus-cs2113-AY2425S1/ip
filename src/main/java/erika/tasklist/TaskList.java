package erika.tasklist;

import erika.console.Console;
import erika.exception.EmptyListException;
import erika.exception.OutOfBoundsException;
import erika.task.Task;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private static ArrayList<Task> tasks;
    private static int taskArraySize;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskArraySize = tasks.size();
    }

    public void deleteTask(int index) throws OutOfBoundsException {
        if (index <= 0 || index > getTaskArraySize()) {
            throw new OutOfBoundsException();
        }
        Console.printDeletedMessage(tasks.get(index - 1));
        tasks.remove(index - 1);
        decrementTaskArraySize();
    }

    public void add(Task task) {
        tasks.add(task);
        incrementTaskArraySize();
        Console.printAddedMessage(task);
    }
    public static void setTaskArraySize(int size) {
        taskArraySize = size;
    }

    public static int getTaskArraySize() {
        return taskArraySize;
    }

    public static void incrementTaskArraySize() {
        taskArraySize++;
    }

    public static void decrementTaskArraySize() {
        taskArraySize--;
    }

    public void markEntry(int index, boolean mark) {
        Task task = tasks.get(index - 1);
        task.setMark(mark);
        if (mark) {
            Console.printMarkedMessage(task);
        } else {
            Console.printUnmarkedMessage(task);
        }
    }

    public String printFileLine() {
        return (String) tasks.stream()
                        .map(Task::generateFileLine)
                        .collect(Collectors.joining(""));
    }

    public void printListMessage() throws EmptyListException {
        if(tasks.isEmpty()) {
            throw new EmptyListException();
        }
        Console.printMessage(printList());
    }

    public String printList() throws EmptyListException{
        return IntStream.range(0, taskArraySize)
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .collect(Collectors.joining("\n\t"));
    }

}
