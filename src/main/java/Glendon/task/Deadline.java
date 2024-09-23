package Glendon.task;
import java.time.LocalDate;

public class Deadline extends Task {
    protected String dueTime;

    public Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    public Deadline(int completed, String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
        if (completed == 1) {
            super.isCompleted = true;
        }
    }

    @Override
    public String saveToFile() {
        return "D|" + super.saveToFile() + "|" + dueTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(dueTime: " + dueTime + ")";
    }
}
