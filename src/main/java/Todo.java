/*
 * Perform Todo's tasks
 */
public class Todo extends Task {

    /* 
     * Construct a Todo task with specified description
     * 
     * @param description The description of the task.  
     */
    public Todo(String description) {
        super(description); 
    }

    /* 
     * Construct a Todo task with specified description and completion status
     * 
     * @param description The description of the task. 
     * @param isDone The completion status of the task.  
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone); 
    }

    /**
     * Returns the string representation of the todo task
     * 
     * @return A string in the format "[T][completion status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString(); 
    } 

    /* 
     * Return the string represention of the event task that will be written in the file 
     * 
     * @return A string generated in the file with the format "T | completion status | description".  
     */
    @Override 
    public String writeFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + description; 
    }
}
