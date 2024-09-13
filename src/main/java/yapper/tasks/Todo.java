package yapper.tasks;

import yapper.io.StringStorage;

public class Todo extends Task {
    public Todo(String taskDesc) {
        super(taskDesc);
    }
    @Override // do I need this?
    public String taskToString() {
        return "[T]" + super.taskToString();
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
