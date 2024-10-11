/** 
 * Perform Event's tasks
 */
public class Event extends Task {

    /* The start time of the event */
    protected String from; 

    /* The end time of the event */
    protected String to; 

    /**  
     * Construct an Event task with specified description, start time and end time  
     * 
     * @param description The description of the task.  
     * @param from The start time of the task. 
     * @param to The end time of the task.  
     */
    public Event(String description, String from, String to) {
        super(description); 
        this.from = from; 
        this.to = to; 
    }

    /**  
     * Construct an Event task with specified description, start time, end time and completion status
     * 
     * @param description The description of the task. 
     * @param from The start time of the task.
     * @param to The end time of the task.  
     * @param isDone The completion status of the task.  
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone); 
        this.from = from; 
        this.to = to; 
    }

    /**
     * Returns the string representation of the event task
     * 
     * @return A string in the format "[E][completion status] description (from: start time to end time)".
     */
    @Override 
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to " + to + ")"; 
    }

    /**  
     * Return the string represention of the event task that will be written in the file 
     * 
     * @return A string generated in the file with the format "E | completion status | description | start time | end time".  
     */
    @Override
    public String writeFile() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to; 
    }
}
