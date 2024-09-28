package doug.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class representing Deadline tasks
 * Inherits from Task class
 */
public class Deadline extends Task {
    protected String by; // used if date and time were not provided for the task
    protected LocalDateTime dateAndTime; // used if task was given both a date and time
    protected LocalDate date; // used if task was only given a date

    /**
     * Constructor to create a new Deadline task
     * Checks if there is a Date and/or Time attached to the deadline, and formats it properly
     *
     * @param description String representing the description of the task
     * @param by String representing deadline of the task
     */
    public Deadline(String description, String by){
        super(description);

        try {
            dateAndTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.by = "";
        }  catch (DateTimeParseException e1) {
            try {
                date = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.by = "";
            } catch (DateTimeParseException e2) {
                this.by = by;
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
        String dateInString;
        if (by.isEmpty()) {
            if (dateAndTime == null) {
                dateInString = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } else {
                dateInString= dateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh.mma"));
            }
        } else {
            dateInString = by;
        }

        return "[D]" + super.toString() + " (by: " + dateInString + ")";
    }

    /**
     * To return a custom string to be written in save file
     *
     * @return String describing the task's details in the proper format for save file
     */
    @Override
    public String saveString() {
        String saveDateInString;
        if (by.isEmpty()) {
            if (dateAndTime == null) {
                saveDateInString = date.toString();
            } else {
                saveDateInString = dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            }
        } else {
            saveDateInString = by;
        }

        return "D | " + super.saveString() + " | " + description + " | " + saveDateInString;
    }
}
