package tasktypes;

/**
 * Represents a Todo task.
 * It is-a task (a type of task)
 * which contains a description.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description, TypeOfTask.ToDo);
    }

    public ToDo(String description, boolean isDone) {
        super(description, TypeOfTask.ToDo, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a character signifying the type of task.
     *
     * @return Character 'T' signifying the type of task the object is
     */
    public char getTaskCharacter() {
        return 'T';
    }


    /**
     * Returns the current state of the object
     * in a format which can be directly written to a file
     *
     * @return a string which is in a storable format
     */
    public String getStorableString() {
        int isDoneInteger = getIsDone() ? 1 : 0;
        return getTaskCharacter() + " | " + isDoneInteger + " | " + getDescription();
    }
}
