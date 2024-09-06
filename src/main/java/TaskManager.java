// manages Tasks for Yapper
public class TaskManager {
    private Task[] tasks;
    private int currTaskTotal;

    // Constructor and Getters
    public TaskManager(int maxCapacity) {
        tasks = new Task[maxCapacity];
        currTaskTotal = 0;
    }
    public Task[] getAllTasks() {
        return tasks;
    }
    public int getCurrTaskTotal() {
        return currTaskTotal;
    }
    public Task getTask(int taskOrdinal) {
        return tasks[taskOrdinal];
    }
    // TaskManager Operations
    public void addTask(Task task) {
        tasks[currTaskTotal] = task;
        currTaskTotal++;
    }
}
