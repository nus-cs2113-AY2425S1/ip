import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {
    protected String byString;
    protected LocalDate byDate;
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;

        // to accommodate both string and datetime inputs

        try {
            this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e){
            this.byDate = null;
        }
    }

    @Override
    public String toString() {
        String displayDate = (byDate != null) ? byDate.toString() : byString;
        return "[D]" + currentStatus() + " " + description + " (by: " + displayDate + ")";
    }

    @Override
    public String toSave() {
        return "D |" + super.toSave() + " | " + byString;
    }

}