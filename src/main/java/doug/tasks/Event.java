package doug.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class representing Event tasks
 * Inherits from Task class
 */
public class Event extends Task{
    protected String from; // used if start date and time were not provided for the task
    protected String to; // used if end date and time were not provided for the task
    protected LocalDateTime fromDateAndTime; // used if task's start was given both a date and time
    protected LocalDate fromDate; // used if task was only given a start date
    protected LocalDateTime toDateAndTime; // used if task's end was given both a date and time
    protected LocalDate toDate; // used if task was only given an end date

    /**
     * Constructor to create a new Event task
     * Checks if there is a Date and/or Time attached to the start and end inputs, and formats it properly
     *
     * @param description String representing the description of the task
     * @param from String representing the start period of the task
     * @param to String representing the end period of the task
     */
    public Event(String description, String from, String to){
        super(description);

        try {
            fromDateAndTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.from = "";
        }  catch (DateTimeParseException e1) {
            try {
                fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.from = "";
            } catch (DateTimeParseException e2) {
                this.from = from;
            }
        }

        try {
            toDateAndTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = "";
        }  catch (DateTimeParseException e1) {
            try {
                toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.to = "";
            } catch (DateTimeParseException e2) {
                this.to = to;
            }
        }
    }

    /**
     * Overload method to return a custom string representing the task
     *
     * @return String describing the task's details
     */
    @Override
    public String toString(){
        String fromInString;
        if (from.isEmpty()) {
            if (fromDateAndTime == null) {
                fromInString = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } else {
                fromInString = fromDateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh.mma"));
            }
        } else {
            fromInString = from;
        }

        String toInString;
        if (to.isEmpty()) {
            if (toDateAndTime == null) {
                toInString = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } else {
                toInString = toDateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh.mma"));
            }
        } else {
            toInString = to;
        }

        return "[E]" + super.toString() + " (from: " + fromInString + " to: " + toInString + ")";
    }

    /**
     * To return a custom string to be written in save file
     *
     * @return String describing the task's details in the proper format for save file
     */
    @Override
    public String saveString() {
        String saveFromInString;
        if (from.isEmpty()) {
            if (fromDateAndTime == null) {
                saveFromInString = fromDate.toString();
            } else {
                saveFromInString = fromDateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            }
        } else {
            saveFromInString = from;
        }

        String saveToInString;
        if (to.isEmpty()) {
            if (toDateAndTime == null) {
                saveToInString = toDate.toString();
            } else {
                saveToInString = toDateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            }
        } else {
            saveToInString = to;
        }

        return "E | " + super.saveString() + " | " + description + " | " + saveFromInString + " | " + saveToInString;
    }
}
