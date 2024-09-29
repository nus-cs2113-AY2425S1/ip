/**
 * The Task class represents an abstract task in the task management system.
 * It contains common functionality for tasks such as marking them as done or not done.
 * This class is intended to be extended by specific task types like Todo, Deadline, or Event.
 */
public abstract class Task {
    String description;
    boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * By default, the task is not completed.
     *
     * @param description The description of the task
     */
    Task (String description) {
        this.description = description;
        this.isDone = false;
    }
    void markAsDone() {
        this.isDone = true;
    }

    void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, indicating whether it is completed
     * and showing the description.
     *
     * @return A string in the format "[X] description" if the task is done,
     * or "[ ] description" if the task is not done
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

}


