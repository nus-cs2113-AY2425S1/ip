/**
 * Represents a Deadline task in the KBot application.
 * A Deadline task has a specific deadline by which it needs to be completed.
 */
public class Deadline extends Task {

    /**
     * Constructs a Deadline task with the specified description and time.
     *
     * @param description The description of the Deadline task.
     * @param time       The time by which the task needs to be completed.
     */
    public Deadline(String description, String time) {
        super(description, time); // Store time
    }

    /**
     * Converts the Deadline task to a string format for file storage.
     * The format is: D | isDone | description | time
     *
     * @return A string representation of the Deadline task for saving to a file.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + time;
    }
}
