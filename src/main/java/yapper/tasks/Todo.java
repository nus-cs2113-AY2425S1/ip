package yapper.tasks;

import yapper.io.StringStorage;

public class Todo extends Task {
    // No Additional Attributes

    // Constructors
    public Todo(String taskDesc) {
        super(taskDesc);
    }
    public Todo(String taskDesc, boolean isDone) {
        super(taskDesc, isDone);
    }
    // No Getters and Setters Yet

    // Task Print Operations for Adding/Removing Data
    @Override
    public String taskToDisplay() {
        return "[" + StringStorage.TODO_SYMBOL +"]"
                + super.taskToDisplay();
    }

    // Task Conversion Operations
    @Override
    public String taskToString() {
        return StringStorage.TODO_SYMBOL
                + StringStorage.STORAGE_DELIMITER + super.taskToString();
    }
    public Todo stringToTask(String taskAsString) {
        Task task = Task.stringToTask(taskAsString);
        return new Todo(task.taskDesc, task.isDone);
    }
}
