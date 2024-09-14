package yapper.tasks;

import yapper.io.StringStorage;

public class Todo extends Task {
    // No Additional Attributes

    // Constructor
    public Todo(String taskDesc) {
        super(taskDesc);
    }

    // toString Method
    @Override
    public String taskToString() {
        return StringStorage.TODO_PREFIX
                + super.taskToString();
    }
}
