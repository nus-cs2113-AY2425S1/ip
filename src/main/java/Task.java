public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Basic Task constructor
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns String depicting whether the task is finished or not.
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getName() {
        return taskName;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void toggleStatus() {
        this.isDone = !this.isDone;
    }
}

