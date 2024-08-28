import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList {

    private final ArrayList<Task> list = new ArrayList<>();
    private int size = 0;

    /**
     * Prints list of tasks with their completion status.
     */
    public void printList() {
        IntStream.range(0, list.size())
                .forEach(x -> System.out.println((x + 1) + "."
                        + "[" + (list.get(x).getStatus() ? "X" : " ") + "] "
                        + list.get(x).getName()));
    }

    /**
     * Adds a new task.
     *
     * @param taskName Name of the new task.
     */
    public void addItem(String taskName) {
        Task task = new Task(taskName);
        this.list.add(task);
        this.size++;
    }

    /**
     * Sets the status of a task in the list.
     * Includes error catching for task index.
     *
     * @param index Index of the task in the list.
     * @param status The task's new status.
     */
    public void setItemStatus(int index, boolean status) {
        try {
            this.list.get(index - 1).setStatus(status);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oh no, the list item does not exist!");
        }
    }
}