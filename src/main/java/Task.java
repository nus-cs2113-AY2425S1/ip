public class Task {
    protected String description; // Description of the task
    protected boolean isDone;     // Status of the task

    private static int numberOfTasks = 0; // Number of tasks in the list

    /**
     * Constructor for the Task class.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

        numberOfTasks++;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the description of the task.
     *
     * @param newDescription
     */
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;

        System.out.print("Nice! I've marked this task as done: \n  ");
        printTask();
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;

        System.out.print("OK, I've marked this task as not done yet: \n  ");
        printTask();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return number of tasks in the list
     */
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Prints the task to the console.
     */
    public void printTask() {
        System.out.println("[" + this.getStatusIcon() + "] " + this.description);
    }
}
