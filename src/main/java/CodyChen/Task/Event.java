package CodyChen.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Deadline {
    protected String to_original;
    protected LocalDate to_parsed;

    public Event(String description, String from, String to) {
        super(description, from);
        this.to_original = to;
        this.type = 'E';
    }

    public Event(String description, LocalDate from, LocalDate to) {
        super(description, from);
        this.to_parsed = to;
        this.type = 'E';
    }

    @Override
    public String formattedEvent() {
        if(to_parsed != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return " to: " + to_parsed.format(formatter) +")";
        }
        return " to: " + to_original + ")";
    }
}

