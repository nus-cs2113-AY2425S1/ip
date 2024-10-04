package melchizedek.task;

import melchizedek.Parser;

import java.time.LocalDate;

public class Deadline extends Task {

    private LocalDate byDate;
    private String byTime;

//    public Deadline(String description, String byDate) {
//        super(description);
//        this.byDate = Parser.parseDate(byDate);
//        this.byTime = null;
//    }

    /**
     * Constructor of the Deadline class.
     *
     * @param description Deadline description
     */
    public Deadline(String description, String byDate, String byTime) {
        super(description);
        this.byDate = Parser.parseDate(byDate);
        this.byTime = byTime;
    }

    /**
     * Constructor of the Deadline class,
     * mainly used when loading tasks from save file only.
     *
     * @param description Deadline description
     * @param isDone Truth value of whether deadline has been marked as done
     */
    public Deadline(String description, boolean isDone, String byDate, String byTime) {
        super(description, isDone);
        this.byDate = Parser.parseDate(byDate);
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
            return "D | " + super.taskToFile() + " | " + Parser.stringDateToFile(byDate) + " | " + byTime;
        }
        return "D | " + super.taskToFile() + " | " + Parser.stringDateToFile(byDate);
    }

    /**
     * Override method to format deadline to printable String
     *
     * @return Deadline in formatted String
     */
    @Override
    public String toString() {
        if (byTime != null) {
            return "[D]" + super.toString() + " (by: " + Parser.stringDate(byDate) + byTime + ")";
        }
        return "[D]" + super.toString() + " (by: " + Parser.stringDate(byDate) + ")";
    }
}
