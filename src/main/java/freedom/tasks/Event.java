package freedom.tasks;

import freedom.exceptions.DescriptionEmpty;
import freedom.exceptions.InvalidDateTime;
import freedom.exceptions.TimeEmpty;
import freedom.user.DateParser;

import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description) throws Exception {
        super(description);
        final int DESCRIPTION_INDEX = 0;
        final int FROM_INDEX = 1;
        final int TO_INDEX = 2;

        String[] components = description.split("/from|/to");
        updateDescription(components[DESCRIPTION_INDEX].trim());
        try {
            if (getDescription().isEmpty()) {
                throw new DescriptionEmpty();
            }
            setFrom(components[FROM_INDEX].trim());
            setTo(components[TO_INDEX].trim());
            if (getFrom().isEmpty() || getTo().isEmpty()) {
                throw new TimeEmpty();
            }
        } catch (DescriptionEmpty e) {
            ui.printEmptyDescriptionError();
            throw new Exception("Description is empty");
        } catch (TimeEmpty | ArrayIndexOutOfBoundsException e) {
            ui.printNoDuration();
            throw new Exception("No from/to time given");
        }
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.isDone = isDone;
        setFrom(from);
        setTo(to);
    }

    public void setFrom(String from) {
        try {
            this.from = DateParser.convertToDateTime(from);
        } catch (InvalidDateTime e) {
            ui.printInvalidDateTime();
        }
    }

    public String getFrom() {
        return DateParser.convertToString(from);
    }

    public void setTo(String to) {
        try {
            this.to = DateParser.convertToDateTime(to);
        } catch (InvalidDateTime e) {
            ui.printInvalidDateTime();
        }
    }

    public String getTo() {
        return DateParser.convertToString(to);
    }

    public String generateTaskLine() {
        return "[E]" + super.generateTaskLine() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }

    public String generateStorageLine() {
        return "E | " + super.generateStorageLine() + " | " + getFrom() + " | " + getTo() + "\n";
    }
}
