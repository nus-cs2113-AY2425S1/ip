package commands;

/**
 * Represent tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor
     * @param description A string containing the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the deadline's status of completion.
     * 1 represents completed tasks, 0 represents uncompleted tasks
     *
     * @return 1 or 0, depending on the task's state of completion
     */
    public int getStatus() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns the string showing the task state of completion
     * @return the string showing the task state of completion
     */
    public String getStatusIcon() {
        return ("[" + (isDone ? "X" : " ") + "]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the formatted task string for display in task list
     * Function is used mainly for overriding by child classes
     * (Todo, Event and Deadline)
     *
     * @return A string containing for display in task list
     */
    public String createTaskList(){
        return "";
    }

    /**
     * Returns the formatted task string for saving in txt
     * Function is used mainly for overriding by child classes
     * (Todo, Event and Deadline)
     *
     * @return A string containing for saving in txt
     */
    public String createTaskTxt(){
        return "";
    }

}
