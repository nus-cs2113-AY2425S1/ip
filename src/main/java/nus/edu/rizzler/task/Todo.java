package nus.edu.rizzler.task;

/**
 * Represents a todo task, which includes a description and a completion status.
 */
public class Todo extends Task{

    /**
     * Constructs a {@code Todo} task with the specified name and completion status.
     *
     * @param taskName The name of the task.
     * @param isDone The completion status of the task.
     */
    public Todo(String taskName, Boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Returns a string representation of the todo task, formatted for display.
     *
     * @return A string representing the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a CSV-formatted string representation of the todo task.
     *
     * @return A CSV string representing the todo task.
     */
    @Override
    public String toCSV(){
        return String.format("T, %s", super.toCSV());
    }
}
