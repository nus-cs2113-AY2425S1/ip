package yapper.tasks;

import yapper.io.StringStorage;

// Task features of Yapper
public class Task {
    protected String taskDesc;
    protected boolean isDone;

    // Constructors
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }
    public Task(String taskDesc, boolean isDone) {
        this.taskDesc = taskDesc;
        this.isDone = isDone;
    }
    // Getters and Setters
    public String getDesc() { return taskDesc }
    public String setDesc(String taskDesc) { this.taskDesc = taskDesc; }
    public String getDoneStatus() {
        return isDone ? StringStorage.NOT_DONE_SYMBOL : StringStorage.IS_DONE_SYMBOL;
    }
    public boolean isDone() { return isDone; }
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }


    // Task Print Operations for Adding/Removing Data
    public String taskToDisplay() {
        return "[" + getDoneStatus() + "] " + taskDesc;
    }
    public void printAddedTask(int taskCount) {
        StringStorage.printWithDividers(
                StringStorage.TASK_ADDED_STRING + "\n"
                + "  " + this.taskToDisplay() + "\n" // spacing is to be consistent with ordinal
                + StringStorage.LIST_SIZE_STRING + taskCount);
    }
    public void printDeletedTask(int taskCount) {
        StringStorage.printWithDividers(
                StringStorage.TASK_DELETED_STRING + "\n"
                + "  " + this.taskToDisplay() + "\n"
                + StringStorage.LIST_SIZE_STRING + taskCount);
    }

    // Task Conversion Operations for Saving/Loading Data
    public String taskToString() {
        return getDoneStatus() + StringStorage.STORAGE_DELIMITER + taskDesc;
    }
    public static Task stringToTask(String taskAsString) {
        String[] taskParts = StringStorage.splitByDelimiter(taskAsString);
        boolean isDone = taskParts[0].equals(StringStorage.IS_DONE_SYMBOL);
        String taskDesc = taskParts[1];
        return new Task(taskDesc, isDone);
    }
}
