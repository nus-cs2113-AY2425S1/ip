package yapper.tasks;

import yapper.io.StringStorage;

/**
 * Todo is a Task with no dates.
 */
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


    /**
     * Converts the todo task to a string format for display,
     * including the Todo symbol.
     *
     * @return a formatted string showing the Todo task's
     * status and description.
     */
    @Override
    public String taskToDisplay() {
        return "[" + StringStorage.TODO_SYMBOL +"]"
                + super.taskToDisplay();
    }
    /**
     * Converts the todo task to a string format for writing to / reading from a file,
     * including the Todo symbol.
     *
     * @return a formatted string representing the Todo task's
     * status and description.
     */
    @Override
    public String taskToString() {
        return StringStorage.TODO_SYMBOL + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString();
    }
}
