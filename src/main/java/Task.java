/**
 * Abstract class representing a generic task.
 */
public abstract class Task {
    protected final String name;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a Task with the given name.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
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
     * Prints the task completion status in string form.
     *
     * @return String representation of task completion status.
     */
    protected String getStatusString() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    /**
     * Creates a task of the specified type with the given parameters.
     *
     * @param type The type of task to create.
     * @param params Additional parameters for the task.
     * @return The created task.
     * @throws IllegalArgumentException If an unknown task type is provided.
     */
    public static Task createTask(String type, String... params) throws IllegalArgumentException {
        return switch (type.toLowerCase()) {
            case "todo" -> new Todo(params[0]);
            case "deadline" -> new Deadline(params[0], params[1]);
            case "event" -> new Event(params[0], params[1], params[2]);
            default -> throw new IllegalArgumentException("Unknown task type: " + type);
        };
    }

    /**
     * Sets the status of the task, then prints an acknowledgement message.
     * If there is redundant task setting, print error message.
     *
     * @param newStatus The task's new status.
     */
    public void setStatus(boolean newStatus) {
        if (newStatus == isDone) {
            System.out.println("Oops, the task has already been marked as " + getStatusString() + "!");
            System.out.println("  " + this);
            return;
        }

        isDone = newStatus;
        System.out.println(newStatus ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:");
        System.out.println("  " + this);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public abstract String toString();
}
