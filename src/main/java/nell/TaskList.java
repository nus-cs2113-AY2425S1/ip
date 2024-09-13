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
        System.out.println("-> The task has been added to the list:");
        tasks[taskCount] = taskToAdd;
        taskCount++;
        System.out.println("   " + taskToAdd);
        System.out.println(String.format("   The list now has %d tasks", this.taskCount));
    }

    public Task getTaskAtIndex(int index) throws IndexOutOfBoundsException,
            NullPointerException {
        return tasks[index];
    }
}
