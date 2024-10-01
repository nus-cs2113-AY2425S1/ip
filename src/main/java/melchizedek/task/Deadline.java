package melchizedek.task;

public class Deadline extends Task {

    private String by;

    /**
     * Constructor of the Deadline class.
     *
     * @param description Deadline description
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of the Deadline class,
     * mainly used when loading tasks from save file only.
     *
     * @param description Deadline description
     * @param isDone Truth value of whether deadline has been marked as done
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Method to convert deadline to the save file format.
     *
     * @return Deadline in formatted String
     */
    @Override
    public String taskToFile() {
        return "D | " + super.taskToFile() + " | " + by;
    }

    /**
     * Override method to format deadline to printable String
     *
     * @return Deadline in formatted String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
