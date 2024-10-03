package Uranus.Tasks;

import UranusExceptions.UranusExceptions;
import UranusExceptions.EmptyDescriptionException;
import UranusExceptions.InvalidEventException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task{

    protected String from;
    protected String to;
    private static final String EVENT_TAG = "E";
    private static final String SEPARATOR = "/";
    private static final String START_TIME_LABEL = "from";
    private static final String END_TIME_LABEL = "to";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Events(String description) throws UranusExceptions {
        super(description, EVENT_TAG);
    }

    private String parseDate(String preParsedDateTime) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(preParsedDateTime, INPUT_FORMAT);
            return dateTime.format(OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            return preParsedDateTime;
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public void setDescription(String description) throws UranusExceptions {
        try {
            String[] str = description.split(SEPARATOR);
            setFrom(parseDate(str[1].substring(START_TIME_LABEL.length()).trim()));
            setTo(parseDate(str[2].substring(END_TIME_LABEL.length()).trim()));
            this.description = str[0] + "(" + START_TIME_LABEL + ": "
                    + from + " " + END_TIME_LABEL + ": " + to + ")";
            if (str[0].isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new InvalidEventException();
            }
        } catch (Exception e) {
            throw new InvalidEventException();
        }
    }
}
