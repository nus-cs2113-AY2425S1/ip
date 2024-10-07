package pythia.task;

import pythia.utility.WriteVisitor;

import java.util.Date;

/**
 * Represents a deadline task that is part of the task management system.
 * A deadline task includes a name and a due date, and can be marked as done or not done.
 */
public class Deadline extends Task {
    private String dueDate;
    private Date dueDateDay;

    /**
     * Constructs a Deadline with the specified name and due date.
     * The deadline is initially marked as not done.
     *
     * @param name     The name of the deadline.
     * @param dueDate  The due date of the deadline.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a Deadline with the specified name, done status, and due date.
     *
     * @param name     The name of the deadline.
     * @param isDone   The initial status of the deadline, indicating whether it is done or not.
     * @param dueDate  The due date of the deadline.
     */
    public Deadline(String name, boolean isDone, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    /**
     * Returns a string representation of the deadline, including its status and due date.
     *
     * @return A string indicating the type of task, its status, and its due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + dueDate + ")";
    }

    /**
     * Accepts a {@link WriteVisitor} to perform a write operation on the deadline.
     *
     * @param visitor The visitor that handles writing the deadline's data to a storage format.
     * @return A string representation of the deadline's data formatted for saving.
     */
    @Override
    public String accept(WriteVisitor visitor) {
        return visitor.visitDeadline(this);
    }
}