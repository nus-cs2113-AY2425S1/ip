package nova;

public class TaskManager {

    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int numberOfTasks;

    public TaskManager() {
        this.tasks = new Task[MAX_TASKS];
        this.numberOfTasks = 0;
    }

    public void checkSpace() throws InsufficientSpaceException {
        if (numberOfTasks >= MAX_TASKS) {
            throw new InsufficientSpaceException("Maximum number of " + MAX_TASKS + " tasks reached.");
        }
    }

    public void addTask(Task task) {
        tasks[numberOfTasks] = task;
        numberOfTasks++;
    }

    public void markTask(int index) {
        tasks[index].markDone();
    }

    public void unmarkTask(int index) {
        tasks[index].unmarkDone();
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void listTasks() {
        MessageDisplay.displaySeparator();
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i].getTaskInfo());
        }
        MessageDisplay.displaySeparator();
    }
}
