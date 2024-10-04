package commands;

/**
 * Represents deadline tasks
 * Inherits description from Task class but include a "by"
 * deadline parameter
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Deadline constructor
     *
     * @param description A string containing the task description
     * @param by A string containing the task deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline's status of completion.
     * [D] represents deadline
     * @return A string containing the deadline symbol with the status icon
     */
    @Override
    public String getStatusIcon() {
        return ("[D]" + super.getStatusIcon());
    }

    /**
     * Returns a formatted deadline for display in task list
     * @return A string containing the task status icon, description,
     * and deadline formatted for the task list.
     */
    @Override
    public String createTaskList() {
        return (getStatusIcon()+  " " +  description + "(by:" + by + ")");
    }

    /**
     * Returns a formatted deadline for saving in the txt file
     * @return A string containing the task status icon, description
     * and deadline formatted for saving
     */
    @Override
    public String createTaskTxt() {
        return ("D | " + super.getStatus() + " | " + description + " | " + by);
    }
}
