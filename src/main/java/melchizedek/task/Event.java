package melchizedek.task;

import melchizedek.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    private LocalDate fromDate;
    private LocalTime fromTime;
    private LocalDate toDate;
    private LocalTime toTime;

    /**
     * Constructor of the Event class.
     *
     * @param description Event description
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Constructor of the Event class,
     * mainly used when loading tasks from save file only.
     *
     * @param description Event description
     * @param isDone Truth value of whether event has been marked as done
     */
    public Event(String description, boolean isDone, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    /**
     * Method to convert event to the save file format.
     *
     * @return Event in formatted String
     */
    @Override
    public String taskToFile() {
        if (fromTime == null && toTime == null) {
            return "E | " + super.taskToFile() + " | " + Parser.stringDateToFile(fromDate) + " | null | " + Parser.stringDateToFile(toDate);
        }
        if (fromTime == null) {
            return "E | " + super.taskToFile() + " | " + Parser.stringDateToFile(fromDate) + " | null | " +
                    Parser.stringDateToFile(toDate) + " | " + Parser.stringTimeToFile(toTime);
        }
        if (toTime == null) {
            return "E | " + super.taskToFile() + " | " + Parser.stringDateToFile(fromDate) + " | " +
                    Parser.stringTimeToFile(fromTime) + " | " + Parser.stringDateToFile(toDate);
        }
        return "E | " + super.taskToFile() + " | " + Parser.stringDateToFile(fromDate) + " | " + Parser.stringTimeToFile(fromTime) +
                " | " + Parser.stringDateToFile(toDate) + " | " + Parser.stringTimeToFile(toTime);
    }

    /**
     * Override method to format event to printable String
     *
     * @return Event in formatted String
     */
    @Override
    public String toString() {
        if (fromTime == null && toTime == null) {
            return "[E]" + super.toString() + " (from: " + Parser.stringDate(fromDate) + " to: " + Parser.stringDate(toDate) + ")";
        }
        if (fromTime == null) {
            return "[E]" + super.toString() + " (from: " + Parser.stringDate(fromDate) + " to: " + Parser.stringDate(toDate) +
                    " " + Parser.stringTime(toTime) + ")";
        }
        if (toTime == null) {
            return "[E]" + super.toString() + " (from: " + Parser.stringDate(fromDate) + " " + Parser.stringTime(fromTime) +
                    " to: " + Parser.stringDate(toDate) + ")";
        }
        return "[E]" + super.toString() + " (from: " + Parser.stringDate(fromDate) + " " + Parser.stringTime(fromTime) +
                " to: " + Parser.stringDate(toDate) + " " + Parser.stringTime(toTime) + ")";
    }
}
