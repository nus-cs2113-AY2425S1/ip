import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskHandler {
    ArrayList<Task> taskArrayList;

    public TaskHandler(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    // Return an unmodifiable view of the taskArrayList
    public List<Task> getTaskArrayList() {
        return Collections.unmodifiableList(taskArrayList);
    }

    public Task markAsDone(String taskNumber) {
        int taskIndex = getTaskIndex(taskNumber);
        if (taskIndex == -1) {
            return null;
        }
        return markTaskAsDone(taskIndex);
    }

    private int getTaskIndex(String taskNumber) {
        if (taskNumber.isBlank()) {
            System.out.println("Please provide a task");
            return -1;
        }
        if (!isValidTaskNumber(taskNumber)) {
            System.out.println("Please provide a valid task number");
            return -1;
        }
        return Integer.parseInt(taskNumber) - 1;
    }

    private boolean isValidTaskNumber(String input) {
        try {
            int index = Integer.parseInt(input);
            return index > 0 && index <= taskArrayList.size();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Task markTaskAsDone(int taskNumber) {
        Task task = taskArrayList.get(taskNumber);
        task.markAsDone();
        return task;
    }

    public Task unmarkAsDone(String taskNumber) {
        int taskIndex = getTaskIndex(taskNumber);
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
        int taskIndex = getTaskIndex(taskNumber);
        if (taskIndex == -1) {
            return null;
        }
        Task task = taskArrayList.get(taskIndex);
        taskArrayList.remove(taskIndex);
        return task;
    }
}
