package bean.task;

import bean.exceptions.InsufficientSpaceException;

public class Deadline extends Task {
    private static final String DELIMITER = "||";

    protected String by;

    // Constructors
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String serialise() {
        return super.serialise() + DELIMITER + by;
    }

}
