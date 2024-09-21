package doug.tasks;

/**
 * Base class for all other task classes to inherit from
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Determines status icon based on isDone status of task
     *
     * @return "X" if done, else returns " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Overload method to return a custom string representing the task
     * Is overriden by child classes to add task specific details
     *
     * @return String describing the task's details
     */
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    /**
     * To return a custom string to be written in save file
     * Is overriden by child classes to add task specific details
     *
     * @return String describing the task's details in the proper format for save file
     */
    public String saveString() {
        return getStatusIcon();
    }

    /**
     * Getter for the description variable
     *
     * @return description variable of the task
     */
    public String getDescription() {
        return description;
    }

}
