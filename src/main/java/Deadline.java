import java.util.Date;

public class Deadline extends Task {
    private Date dueDate;

    public Deadline(String name, boolean status, Date dueDate) {
        super(name, status);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + " (by: " + dueDate + ")";
    }
}
