package tasks;

public abstract class Task {

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

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String getTypeIcon() {
        return typeIcon;
    }


    // Abstract methods to be implemented by subclasses
    public abstract String getBy(); // Allows "by" data access in Deadline-subtype tasks
    public abstract String getEventStart();  // Allows "From" data access in Event-subtype tasks
    public abstract String getEventEnd(); // Allows "To" data access in Event-subtype tasks
}
