public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        // Initialize a new task with a description and set it as not done
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // Return "X" if the task is done, otherwise a space
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        // Mark the task as done
        this.isDone = true;
    }

    public void markAsNotDone() {
        // Mark the task as not done
        this.isDone = false;
    }

    @Override
    public String toString() {
        // Return a string representation of the task
        return "[" + getStatusIcon() + "] " + description;
    }
}
