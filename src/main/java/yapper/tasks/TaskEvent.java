package yapper.tasks;

import yapper.StringStorage;

public class TaskEvent extends Task {
    protected String startDate;
    protected String endDate;
    public TaskEvent(String taskDesc, String startDate, String endDate) {
        super(taskDesc);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String taskToString() {
        return "[E]" + super.taskToString() + ", from " + startDate + " to " + endDate;
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
