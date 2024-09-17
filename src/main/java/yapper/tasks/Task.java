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
    public void printAddedTask(int taskCount) {
        StringStorage.printWithDividers(
                StringStorage.TASK_ADDED_STRING + "\n"
                + "  " + this.taskToString() + "\n"
                + StringStorage.LIST_SIZE_STRING + taskCount);
    }
    // Task Conversion Operations
    public String taskToString() {
        return String.format("%d | %s", isDone ? "X" : " ", taskDesc);
    }
    public static Task stringToTask(String taskAsString) {
        String[] parts = taskAsString.split(" \\| ");
        String isDone = parts[1];
        String taskDesc = parts[2];
        Task task = new Task(taskDesc);
        task.setDoneStatus( isDone.equals("X") );
        return task;
    }
}
