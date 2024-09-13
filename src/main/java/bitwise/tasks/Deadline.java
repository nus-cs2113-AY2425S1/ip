package bitwise.tasks;

import bitwise.constants.Constants;

public class Deadline extends Task {

    protected String deadline;
    protected static final String symbol = Constants.ICON_DEADLINE;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return symbol + " " + super.toString() + "(by: " + deadline + ")";
    }
}
