package yapper.tasks;

import yapper.StringStorage;

// Task features of Yapper
public class Task {
    protected String taskDesc;
    protected boolean isDone;

    // Constructor
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }
    // Task Operations
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String taskToString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskDesc;
    }
    public void printAddedTask(int taskCount) {
        System.out.println(StringStorage.LINE_DIVIDER);
        System.out.println(StringStorage.TASK_ADDED_STRING);
        System.out.println( "  " + this.taskToString() );
        System.out.println(StringStorage.LIST_SIZE_STRING + taskCount);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
