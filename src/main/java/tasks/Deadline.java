package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    // private String by;
    private LocalDate by;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    public String getBy() {
        return displayFormatter.format(by);
    }

    public String getByAsCommand() {
        return saveFormatter.format(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getBy());
    }

    @Override
    public String getTaskAsCommand() {
        return String.format("deadline %s /by %s", getTaskName(), getByAsCommand());
    }
}
