package erika.tasklist;

import erika.console.Console;
import erika.exception.EmptyListException;
import erika.exception.OutOfBoundsException;
import erika.task.Task;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/**
 * Represents a list of <code>Tasks</code>
 */
public class TaskList {
    private static ArrayList<Task> tasks;
    private static int taskArraySize;
    /**
     * @param tasks <code>ArrayList<Task></code> that represents a list of <code>Task</code>s
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskArraySize = tasks.size();
    }
    /**
     * Deletes a <code>Task</code> from the list
     * @param index represnets the index of the list we wish to delete
     * @throws OutOfBoundsException when index is out of the range of the list
     */
    public void deleteTask(int index) {
        tasks.remove(index);
        decrementTaskArraySize();
    }

    public void deleteAll() {
        tasks.removeAll(tasks);
        setTaskArraySize(0);
    }
    /**
     * Adds a <code>Task</code> to the list
     * @param task represnets the task to be added to the list
     */
    public void add(Task task) {
        tasks.add(task);
        incrementTaskArraySize();
    }
    /**
     * Setter to set the static variable <code>int taskArraySize</code>
     * @param size represnets the new size to be set
     */
    public static void setTaskArraySize(int size) {
        taskArraySize = size;
    }
    /**
     * Getter to obtain the static variable <code>int taskArraySize</code>
     * @return The size of the array
     */
    public static int getTaskArraySize() {
        return taskArraySize;
    }
    /**
     * Setter to increment the static variable <code>int taskArraySize</code> by 1
     * Used when adding a <code>Task</code>
     */
    private static void incrementTaskArraySize() {
        taskArraySize++;
    }
    /**
     * Setter to increment the static variable <code>int taskArraySize</code> by 1
     * Used when adding a <code>Task</code>
     */
    private static void decrementTaskArraySize() {
        taskArraySize--;
    }
    /**
     * Marks an entry in the <code>TaskList</code> as done/not done
     * @param index Index of list entry to be marked as done/not done
     * @param mark whether the task is done (true : done , false : not done)
     */
    public void markEntry(int index, boolean mark) {
        Task task = tasks.get(index - 1);
        task.setMark(mark);
        if (mark) {
            Console.printMarkedMessage(task);
        } else {
            Console.printUnmarkedMessage(task);
        }
    }
    /**
     * Converts the Task array into a string to be written to the text file for storage
     * @return String to be written to the text file
     */
    public String printFileLine() {
        return (String) tasks.stream()
                        .map(Task::generateFileLine)
                        .collect(Collectors.joining(""));
    }
    /**
     * Converts the Task array into a string to be written to the console
     * @throws EmptyListException when list is empty
     * @return String to be written to the console
     */
    public void printListMessage() throws EmptyListException {
        if(tasks.isEmpty()) {
            throw new EmptyListException();
        }
        Console.printMessage(printList());
    }
    /**
     * Helper function to generate the String for printing to the console
     * @return String to be written to the text file
     * @throws EmptyListException when the list is empty
     */
    public String printList() throws EmptyListException {
        return IntStream.range(0, taskArraySize)
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .collect(Collectors.joining("\n\t"));
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
        incrementTaskArraySize();
    }
}
