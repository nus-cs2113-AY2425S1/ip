package Tasks;

/**
 * Represents a task that the user wishes to jot down.
 */
public class Task {
     protected String description;
     protected boolean isDone;

     public Task(String description) {
         this.description = description;
         this.isDone = false;
     }

    /**
     * Returns description of task
     * @return description of task
     */
     public String getDescription() {
         return description;
     }

    /**
     * Returns status of task completion
     * @return true if task is done, else false
     */
     public boolean isDone() {
         return isDone;
     }

    /**
     * Sets task as done
     */
     public void markAsDone() {
         isDone = true;
     }

    /**
     * Sets task as undone
     */
    public void markAsUndone(){
         isDone = false;
     }
     public String toString(){
         if (isDone()){
             return "[X] " + getDescription();
         }
         return "[] " + getDescription();
     }
 }
