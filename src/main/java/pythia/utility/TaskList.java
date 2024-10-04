package pythia.utility;
import pythia.task.Task;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private int remainingTasks = 0;      // number of tasks which are not done yet
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

    @Override
    public String toString() {
        StringBuilder taskListString = new StringBuilder();
        for (int i = 0; i < remainingTasks - 1; i++) {
            taskListString.append(i + 1).append(". ");
            taskListString.append(taskList.get(i).toString());
            taskListString.append("\n");
        }
        int lastIndex = remainingTasks - 1;
        taskListString.append(lastIndex + 1).append(". ");
        taskListString.append(taskList.get(lastIndex).toString());

        return taskListString.toString();
    }
}
