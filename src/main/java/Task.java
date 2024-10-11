public abstract class Task {
    
    protected String description; 
    protected boolean isDone; 

    /**  
     * Construct a task with specified description
     * 
     * @param description The description of the task.  
     */
    public Task (String description) {
        this.description = description; 
        this.isDone = false; 
    }

    /**  
     * Construct a task with specified description
     * 
     * @param description The description of the task.  
     * @param isDone The completion status of the task.  
     */
    public Task (String description, boolean isDone) {
        this.description = description; 
        this.isDone = isDone; 
    }
   
    /**  
     * Mark the status completion of the task
     */
    public void markDone() {
        this.isDone = true; 
    }

    /**  
     * Unmark the status completion of the task
     */
    public void unmarkDone() {
        this.isDone = false; 
    }

    /**  
     * Returns the completion status of the task in a formatted string.
     *
     * @return "[X]" if the task is done, "[ ]" if it is not done.
     */
    public String isDone() {
        if (isDone) {
            return "[X]"; 
        } else {
            return "[ ]"; 
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description as a string.
     */
    public String getDescription() {
        return description; 
    }

    /**
     * Return the string representation of a task
     *
     * @return A string in the format "[completion status] description".
     */
    @Override 
    public String toString() { 
        return isDone() + " " + description; 
    }

    /**
     * Abstract method to save the task into the storage file. 
     *
     * @return A string representation of the task for saving to a file.
     */
    public abstract String writeFile(); 
}
