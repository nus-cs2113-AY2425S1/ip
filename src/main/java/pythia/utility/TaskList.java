package pythia.utility;
import pythia.task.Task;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static int remainingTasks = 0;      // number of tasks which are not done yet
                                                // might be different from array size

    public TaskList() {}

    public int getNumberOfRemainingTasks() {
        return remainingTasks;
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
        remainingTasks++;
    }

    public void remove(int taskNumber) throws IndexOutOfBoundsException {
        if (taskNumber < 0 || taskNumber >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.remove(taskNumber);
        remainingTasks--;
    }

    public Task get(int taskNumber) {
        return taskList.get(taskNumber);
    }

    public void markAsDone(int taskNumber) {
        if (taskNumber < 0 || taskNumber >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(taskNumber).markAsDone();
        remainingTasks--;
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
