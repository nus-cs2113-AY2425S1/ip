package pythia.task;

import pythia.utility.WriteVisitor;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    public Deadline(String name, boolean isDone, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + dueDate + ")";
    }

    @Override
    public String accept(WriteVisitor visitor) {
        return visitor.visitDeadline(this);
    }
}