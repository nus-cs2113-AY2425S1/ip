package erika.task;
/**
 * Represents a deadline task
 * Inherits Task class
 */
public class Deadline extends Task {
    protected String deadline;
    /**
     * @param description <code>String</code> that represents the description of the deadline
     * @param deadline <code>String</code> that represents a textual representation of the deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    /**
     * Overrides the <code>toString</code> method and prints out the deadline in a specific format for printing to console
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X":" ", description, deadline);
    }
    /**
     * Overrides the <code>toString</code> method and prints out the deadline in a specific format for writing to text file
     */
    @Override
    public String generateFileLine() {
        return String.format("D,%s,%s,%s\n", isDone ? "1" : "0", description, deadline);
    }
}
