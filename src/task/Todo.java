package task;

/**
 * The Todo class represents a to-do task.
 * It extends the abstract Task class and provides implementations for formatting the task's display output and file-saving format.
 */
public class Todo extends Task {

    public Todo(String taskName, boolean isMarked) {
        super(taskName, isMarked);
    }

    /**
     * Returns the string representation of the to-do task for display.
     * The format includes "[T]", followed by the task's completion status and name.
     *
     * @return A string representing the to-do task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the to-do task to a format suitable for saving to a file.
     * The format is "T | isMarked | taskName".
     *
     * @return A string formatted for file storage of the to-do task.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isMarked()? "1":"0") + " | " + getTaskName();
    }
}
