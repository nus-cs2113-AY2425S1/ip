package yapper.tasks;

import yapper.io.StringStorage;

// Task features of Yapper
public class Task {
    protected String taskDesc;
    protected boolean isDone;

    // Constructor
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }

    // Task Getters and Setters
//    public String getDesc() { return taskDesc }
//    public String setDesc(String taskDesc) { this.taskDesc = taskDesc; }
    public boolean isDone() { return isDone; }
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }

    // Task Print Operations
    public String taskToString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskDesc;
    }
    public void printAddedTask(int taskCount) {
        StringStorage.printWithDividers(
                StringStorage.TASK_ADDED_STRING + "\n"
                + "  " + this.taskToString() + "\n"
                + StringStorage.LIST_SIZE_STRING + taskCount);
    }
    public void printDeletedTask(int taskCount) {
        StringStorage.printWithDividers(
                StringStorage.TASK_DELETED_STRING + "\n"
                + "  " + this.taskToString() + "\n"
                + StringStorage.LIST_SIZE_STRING + taskCount);
    }
}
