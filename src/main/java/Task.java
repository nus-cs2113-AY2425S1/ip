/**
 * contains information of each task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructor of Task
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get the state of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * get the description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * set the state of task to be done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * get the formatted string when a task is converted to string
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

