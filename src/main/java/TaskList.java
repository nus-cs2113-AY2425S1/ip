import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList {

    private final ArrayList<Task> list = new ArrayList<>();
    private int size;

    TaskList() {
        this.size = 0;
    }

    public void printList() {
        IntStream.range(0, list.size())
                .forEach(x -> System.out.println((x + 1) + "."
                        + "[" + (list.get(x).getStatus() ? "X" : " ") + "] "
                        + list.get(x).getName()));
    }

    public void addItem(String taskName) {
        Task task = new Task(taskName);
        this.list.add(task);
        this.size++;
    }

    public void setItemStatus(int index, boolean status) {
        try {
            this.list.get(index - 1).setStatus(status);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oh no, the list item does not exist!");
        }
    }
}