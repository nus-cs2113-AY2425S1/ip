package erika.task;
/**
 * Represents an event task
 * Inherits Task class
 */
public class Todo extends Task{
    /**
     * @param description <code>String</code> that represents the description of the todo
     */
    public Todo (String description) {
        super(description);
    }
    /**
     * Overrides the <code>toString</code> method and prints out the deadline in a specific format for printing to console
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X":" ", description);
    }
    /**
     * Overrides the <code>toString</code> method and prints out the deadline in a specific format for printing to console
     */
    @Override
    public String generateFileLine() {
        return String.format("T,%s,%s\n", isDone ? "1" : "0", description);
    }
}
