package yapper.tasks;

import yapper.io.StringStorage;

/**
 * Todo is a Task with no dates.
 */
public class Todo extends Task {
    // No Additional Attributes

    // Constructors
    /**
     * Constructs a Todo task with the specified description and
     * initializes its completion status to false (not done).
     *
     * <p> Used when reading from user input. </p>
     *
     * @param taskDesc the description of the todo task
     */
    public Todo(String taskDesc) {
        super(taskDesc);
    }
    /**
     * Constructs a Todo task with the specified description and completion status.
     *
     * <p> Used when reading from file. </p>
     *
     * @param taskDesc the description of the todo task
     * @param isDone the completion status of the todo task
     */
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
        return "[" + StringStorage.SYMBOL_TODO +"]"
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
        return StringStorage.SYMBOL_TODO + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString();
    }
}
