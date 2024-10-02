import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
        this.by = LocalDate.parse(by, formatter);
    }
//    public void setBy(String by) {
//        this.by = by;
//    }
//    public String getBy() {
//        return by;
//    }

    @Override
    public String toString() {
        return "[D]" + currentStatus() + " " + description + " (by: " + by + ")";
    }

    @Override
    public String toSave() {
        return "D |" + super.toSave() + " | " + by;
    }

}