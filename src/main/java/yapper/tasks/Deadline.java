package yapper.tasks;

import yapper.io.StringStorage;

/**
 * Deadline is a Task with an end date.
 */
public class Deadline extends Task {
    // Additional Attributes
    protected String endDate;

    // Constructor
    public Deadline(String taskDesc,
                    String endDate) {
        super(taskDesc);
        this.endDate = endDate;
    }
    public Deadline(String taskDesc, boolean isDone,
                    String endDate) {
        super(taskDesc);
        this.isDone = isDone;
        this.endDate = endDate;
    }
    // No Getters and Setters Yet


   /**
     * Converts the deadline task to a string format for display,
     * including the Deadline symbol and the end date.
     *
     * @return a formatted string showing the Deadline task's
    * status, description and end date.
     */
    @Override
    public String taskToDisplay() {
        return "[" + StringStorage.DEADLINE_SYMBOL + "]"
                + super.taskToDisplay() + ", by " + endDate;
    }
    /**
     * Converts the deadline task to a string format for writing to / reading from a file,
     * including the Deadline symbol and the end date.
     *
     * @return a formatted string representing the deadline task's
     * status, description and end date.
     */
    @Override
    public String taskToString() {
        return StringStorage.DEADLINE_SYMBOL + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " " + endDate;
    }
}
