package pythia.task;

import pythia.utility.WriteVisitor;

/**
 * Represents a task with a name and a completion status (whether it's done or not).
 * It provides methods for marking the task as done or not done, and allows for
 * string representations of the task for display and saving purposes.
 */
public class Task implements Savable {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task.
     * The string indicates whether the task is done or not, followed by the task name.
     *
     * @return A string in the format: "[X] <task name>" if done, "[ ] <task name>" if not done.
     */
    @Override
    public String toString() {
        if (isDone == false) {
            return "[ ] " + name;
        } else {
            return "[X] " + name;
        }
    }

    /**
     * Accepts a visitor to perform a write operation on this task for saving purposes.
     *
     * @param visitor The {@link WriteVisitor} that handles writing the task to a storage format.
     * @return The formatted string representation of this task.
     */
    @Override
    public String accept(WriteVisitor visitor) {
        return visitor.visitTask(this);
    }
}
