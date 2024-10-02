import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskHandler {
    private Fenix fenix;
    private ArrayList<Task> taskArrayList;

    public TaskHandler(Fenix fenix) {
        this.fenix = fenix;
        this.taskArrayList = new ArrayList<>();
    }

    // Return an unmodifiable view of the taskArrayList
    public List<Task> getTaskArrayList() {
        return Collections.unmodifiableList(taskArrayList);
    }

    public Task markAsDone(String taskNumber) {
        int taskIndex = fenix.getTaskIndexFromInput(taskNumber);
        if (taskIndex == -1) {
            return null;
        }
        return markTaskAsDone(taskIndex);
    }

    private Task markTaskAsDone(int taskNumber) {
        Task task = taskArrayList.get(taskNumber);
        task.markAsDone();
        return task;
    }

    public Task unmarkAsDone(String taskNumber) {
        int taskIndex = fenix.getTaskIndexFromInput(taskNumber);
        if (taskIndex == -1) {
            return null;
        }
        return unmarkTaskAsDone(taskIndex);
    }

    private Task unmarkTaskAsDone(int taskNumber) {
        Task task = taskArrayList.get(taskNumber);
        task.unmarkAsDone();
        return task;
    }

    public void storeTask(Task task) {
        taskArrayList.add(task);
    }

    public Task deleteTask(String taskNumber) {
        int taskIndex = fenix.getTaskIndexFromInput(taskNumber);
        if (taskIndex == -1) {
            return null;
        }
        Task task = taskArrayList.get(taskIndex);
        taskArrayList.remove(taskIndex);
        return task;
    }
}
