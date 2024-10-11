package tasktypes;

/**
 * Represents a type of task (it is-a Task)
 * which contains a description and a time
 */
public class Deadline extends Task {
    private final String by;


    public Deadline(String description, String by) {
        super(description, TypeOfTask.Deadline);
        this.by = by;
    }


    public Deadline(String description, String by, boolean isDone) {
        super(description, TypeOfTask.Deadline, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a character signifying the type of task.
     *
     * @return Character 'D' signifying the type of task the object is
     */
    public char getTaskCharacter() {
        return 'D';
    }

    /**
     * Returns the current state of the object
     * in a format which can be directly written to a file
     *
     * @return a string which is in a storable format
     */
    public String getStorableString() {
        int isDoneInteger = getIsDone() ? 1 : 0;
        return getTaskCharacter() + " | " + isDoneInteger +
                " | " + getDescription() + " | " + getBy();
    }
}
