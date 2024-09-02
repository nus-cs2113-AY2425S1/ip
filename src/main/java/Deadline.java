public class Deadline extends Task{
    protected final String by;

    /**
     * Constructor for Deadline
     *
     * @param name Name of the deadline.
     * @param by Date when the deadline is due.
     */
    public Deadline(String name, String by) {
        super(name);
        this.type = "D";
        this.by = by;
    }

    /**
     * Prints the deadline and its status in one line.
     * Overwrites toString method in Java Object class.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (by: " + this.by + ")";
    }
}
