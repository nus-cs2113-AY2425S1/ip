package melchizedek.task;

import melchizedek.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    private LocalDate byDate;
    private LocalTime byTime;

    /**
     * Constructor of the Deadline class.
     *
     * @param description Deadline description
     * @param isDone Truth value of whether deadline has been marked as done
     * @param byDate Date of deadline
     * @param byTime Time of deadline
     */
    public Deadline(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        super(description, isDone);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Method to convert deadline to the save file format.
     *
     * @return Deadline in formatted String
     */
    @Override
    public String taskToFile() {
        if (byTime != null) {
            return "D | " + super.taskToFile() + " | " + Parser.stringDateToFile(byDate) + " | " + Parser.stringTimeToFile(byTime);
        }
        return "D | " + super.taskToFile() + " | " + Parser.stringDateToFile(byDate);
    }

    /**
     * Override method to format deadline to printable String.
     *
     * @return Deadline in formatted String
     */
    @Override
    public String toString() {
        if (byTime != null) {
            return "[D]" + super.toString() + " (by: " + Parser.stringDate(byDate) + " " + Parser.stringTime(byTime) + ")";
        }
        return "[D]" + super.toString() + " (by: " + Parser.stringDate(byDate) + ")";
    }
}
