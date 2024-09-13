package nell;

import nell.tasks.Task;

public class TaskList {
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void addTask(Task taskToAdd) {
        tasks[taskCount] = taskToAdd;
        taskCount++;
    }

    public Task getTaskAtIndex(int index) throws IndexOutOfBoundsException,
            NullPointerException {
        return tasks[index];
    }
}
