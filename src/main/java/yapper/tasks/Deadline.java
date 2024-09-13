package yapper.tasks;

import yapper.stringHandlers.StringStorage;

public class Deadline extends Task {
    protected String endDate;
    public Deadline(String taskDesc, String endDate) {
        super(taskDesc);
        this.endDate = endDate;
    }
    @Override
    public String taskToString() {
        return "[D]" + super.taskToString() + ", due " + endDate;
    }
    @Override
    public void printAddedTask(int taskCount) {
        System.out.println(StringStorage.LINE_DIVIDER);
        System.out.println(StringStorage.TASK_ADDED_STRING);
        System.out.println( "  " + this.taskToString() );
        System.out.println(StringStorage.LIST_SIZE_STRING + taskCount);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
