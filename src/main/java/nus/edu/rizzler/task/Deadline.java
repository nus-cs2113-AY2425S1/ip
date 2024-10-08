package nus.edu.rizzler.task;

/**
 * Represents a task with a deadline, including a description and a due date.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a {@code Deadline} task with the specified name, completion status, and due date.
     *
     * @param taskName The name of the task.
     * @param isDone The completion status of the task.
     * @param by The due date of the task.
     */
    public Deadline(String taskName, Boolean isDone, String by) {
        super(taskName, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    /**
     * Returns a CSV-formatted string representation of the deadline task.
     *
     * @return A CSV string representing the deadline task.
     */
    @Override
    public String toCSV(){
        return String.format("D, %s, %s", super.toCSV(), by);
    }
}
