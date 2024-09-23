import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Todo {

    protected String from_original;
    protected LocalDate from_parsed;

    public Deadline(String description, String from) {
        super(description);
        this.from_original = from;
        this.type = 'D';
    }

    public Deadline(String description, LocalDate from) {
        super(description);
        this.from_parsed = from;
        this.type = 'D';
    }

    @Override
    public String formattedDeadline() {
        if(from_parsed != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return from_parsed.format(formatter);
        }
        return from_original;
    }
}