package yapper.tasks;

import yapper.io.StringStorage;

public class Todo extends Task {
    // No Additional Attributes

    // Constructor
    public Todo(String taskDesc) {
        super(taskDesc);
    }

    // Task Conversion Operations
    @Override
    public String taskToString() {
        return "T" + " | " + super.taskToString();
    }
    @Override
    public static Todo stringToTask(String taskAsString) {
        String[] parts = taskAsString.split(" \\| ");
        String isDone = parts[1];
        String taskDesc = parts[2];
        Todo todo = new Todo(taskDesc);
        todo.setDoneStatus( isDone.equals("X") );
        return todo;
    }
}
