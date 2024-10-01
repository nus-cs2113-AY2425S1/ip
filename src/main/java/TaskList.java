import java.util.ArrayList;
public class TaskList {
    private Task[] tasks = new Task[100];
    private int lastIndex = 0;

    public void add(Task task) {
        tasks[lastIndex] = task;
        lastIndex++;
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < lastIndex; i++) {
            if (tasks[i].getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(tasks[i]);
            }
        }
        return foundTasks;
    }

    public void delete(int index) {
        for (int i = index; i < lastIndex - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[lastIndex - 1] = null;
        lastIndex--;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return lastIndex;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public void markTask(int index, boolean isDone) {
        tasks[index].setDone(isDone);
    }
}
