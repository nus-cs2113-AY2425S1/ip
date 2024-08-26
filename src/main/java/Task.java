/**
 * Defines a task with a description and completion status.
 */
class Task {

    /** Description of the task. */
    protected String description;

    /** Indicates task completion status. */
    protected boolean isComplete;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description Describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /** Sets task completion status to be true. */
    public void setComplete(){
        this.isComplete = true;
    }

    /** Sets task completion status to be false. */
    public void setIncomplete(){
        this.isComplete = false;
    }

    /**
     * Returns an indicator for task completion status.
     *
     * @return "X" if completed or " " if not completed.
     */
    public String getStatusIcon() {
        return(isComplete ? "X" : " ");
    }
};