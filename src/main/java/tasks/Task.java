package tasks;

/**
 * The {@code Task} class is an abstract representation of a generic task, providing basic functionality
 * that can be extended by concrete task types such as {@code ToDo}, {@code Deadline}, and {@code Event}.
 * This class manages the task's name, status (whether it is done or not), and provides abstract methods
 * for task-specific information like deadlines or event timings.
 */
public abstract class Task {

    /**
     * The default type icon for a task. This is used if no specific type icon is defined
     * by subclasses. By default, it is set to {@code "[X]"}.
     */
    static final String typeIcon = "[X]";

    String taskName;

    boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone(boolean newStatus) {
        this.isDone = newStatus;
    }

    public String getTaskName() {
        return this.taskName;
    }

     public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns an icon representing the task's status.
     * If the task is done, it returns {@code "[X]"}, otherwise it returns {@code "[ ]"}.
     *
     * @return a status icon as a {@code String}.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns the task's type icon. Subclasses will override this to provide specific type icons.
     *
     * @return a type icon as a {@code String}. The default icon is {@code "[X]"}.
     */
    public String getTypeIcon() {
        return typeIcon;
    }

    /**
     * An abstract method to retrieve the due date or deadline for deadline-type tasks.
     * Must be implemented by subclasses.
     *
     * @return the due date as a {@code String} for {@code Deadline} tasks, or {@code null} for other task types.
     */
    public abstract String getBy();

    /**
     * An abstract method to retrieve the start time of event-type tasks.
     * Must be implemented by subclasses.
     *
     * @return the event start time as a {@code String} for {@code Event} tasks, or {@code null} for other task types.
     */
    public abstract String getEventStart();

    /**
     * An abstract method to retrieve the end time of event-type tasks.
     * Must be implemented by subclasses.
     *
     * @return the event end time as a {@code String} for {@code Event} tasks, or {@code null} for other task types.
     */
    public abstract String getEventEnd();
}
