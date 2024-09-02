public class Task {
    protected final String name;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor for Task.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.type = "";
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the type of task.
     *
     * @return Name of the task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Completion status of the task.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Sets the status of the task, then prints an acknowledgement message.
     * If there is redundant task setting, print error message.
     *
     * @param newStatus The task's new status.
     */
    public void setStatus(boolean newStatus) {
        String completion = newStatus ? "X" : " ";

        if (newStatus == this.isDone) {
            System.out.println("Oops, the task has already been marked as " + "[" + completion + "]!");
            System.out.println("  " + this);
            return;
        }

        this.isDone = newStatus;
        if (newStatus) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println("  " + this);
    }
}
