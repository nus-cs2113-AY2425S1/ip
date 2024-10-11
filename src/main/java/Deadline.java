/** 
 * Perform Deadline's tasks
 */
public class Deadline extends Task {

    /* The due date of the deadline */
    protected String by; 

    /**  
     * Construct a Deadline task with specified description and deadline 
     * 
     * @param description The description of the task.  
     * @param by The deadline of the task. 
     */
    public Deadline(String description, String by) {
        super(description); 
        this.by = by; 
    }

    /** 
     * Construct a Deadline task with specified description, deadline and completion status
     * 
     * @param description The description of the task.
     * @param by The due date or time by which the task needs to be completed.
     * @param isDone The completion status of the task (true if completed, false otherwise). 
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone); 
        this.by = by; 
    }

    /**
     * Returns the string representation of the deadline task
     * 
     * @return A string in the format "[D][completion status] description (by: due date)".
     */
    @Override 
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**  
     * Return the string represention of the deadline task that will be written in the file 
     * 
     * @return A string generated in the file with the format "D | completion status | description | due date".  
     */
    @Override
    public String writeFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by; 
    }
}
