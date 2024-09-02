import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList {

    private final ArrayList<Task> list = new ArrayList<>();
    private int size = 0;

    /**
     * Returns size of TaskList
     */
    public int size() {
        return this.size;
    }

    /**
     * Prints list of tasks with their completion status.
     */
    public void printList() {
        if (this.size == 0) {
            System.out.println("Uh oh, the list is empty!");
            return;
        }
        IntStream.range(0, list.size())
                .forEach(x -> System.out.println( (x + 1) + "." + list.get(x)));
    }

    /**
     * Adds a new task.
     *
     * @param task The task object to be added.
     */
    public void addItem(Task task) {
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