package ellio.task;

import ellio.Ellio;
import ellio.EllioExceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected String eventStartTime;
    protected String eventEndTime;
    protected LocalDateTime eventStartDateTime;
    protected LocalDateTime eventEndDateTime;

    /**
     * Constructor for Event task creation
     * @param description
     * @param isDone
     * @param eventStartTime
     * @param eventEndTime
     * @throws EllioExceptions
     */
    public Event(String description, String isDone, String eventStartTime, String eventEndTime) throws EllioExceptions {
        super(description, isDone);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventStartDateTime = convertLocalDateTime(eventStartTime);
        this.eventEndDateTime = convertLocalDateTime(eventEndTime);
    }

    /**
     * Converts user input time in String format to a LocalDateTime object
     * @param time String format time.
     * @return LocalDateTime object to be saved into the event object
     * @throws EllioExceptions
     */
    public LocalDateTime convertLocalDateTime(String time) throws EllioExceptions {
        String[] Date = time.split(" ", 2);
        LocalDateTime parsedLocalDateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            parsedLocalDateTime = LocalDateTime.parse(Date[1], formatter);
        } catch (Exception e) {
            throw new EllioExceptions.InvalidDateTimeFormatException();
        }
        return parsedLocalDateTime;
    }

    /**
     * Converts a LocalDateTime object to a MMM dd yyyy HH:mm format String object to be displayed to user
     * @param dateTime
     * @return String Object to be printed to user
     */
    public String getEventTime(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return dateTime.format(formatter);
    }


    /**
     * Creates a Custom display message string to be displayed to user
     * @return String object to be printed to user
     */
    public String getTaskInfo(){
        return "[E]" + super.getTaskInfo() + " (From: " + getEventTime(eventStartDateTime) + " | To: " +
                getEventTime(eventEndDateTime) + ")";
    }

    public String getSaveFileTask(){
        return " e " + super.getSaveFileTask() + " | " + eventStartTime + " | " + eventEndTime;
    }
}
