package Uranus.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {

    protected String by;
    private static final String DEADLINE_TAG = "D";
    private static final String DEADLINE_SEPARATOR = "/by";
    private static final String DEADLINE_LABEL = "by: ";
    private static final DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Deadlines(String description) {
        super(description, DEADLINE_TAG);
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    // Method to check if 'by' is in the date format and parse it
    private String parseDate(String by) {
        try {
            LocalDateTime  dateTime = LocalDateTime.parse(by, DATE_TIME_INPUT_FORMAT);
            return dateTime.format(DATE_TIME_OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            return by;
        }
    }

    @Override
    public void setDescription(String description) {
        int separatorIndex = description.indexOf(' ');
        String[] str = description.split(DEADLINE_SEPARATOR);
        setBy(parseDate(str[1].trim()));
        this.description = description.substring(separatorIndex + 1, description.indexOf(DEADLINE_SEPARATOR))
                + " (" + DEADLINE_LABEL + getBy() + ")";
    }
}
