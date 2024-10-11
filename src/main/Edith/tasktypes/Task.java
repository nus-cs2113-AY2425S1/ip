package tasktypes;

/**
 * Represents a Task.
 * It is an abstract class that is used to provide properties and behaviours that is common to all Tasks.
 * It also specifies certain methods that compulsorily has to be implemented by its subclasses.
 */
abstract public class Task {
    private final String description;
    private boolean isDone;
    public TypeOfTask typeOfTask;


    public Task(String description, TypeOfTask typeOfTask) {
        this.description = description;
        this.isDone = false;
        this.typeOfTask = typeOfTask; //has potential to create error
    }


    public Task(String description, TypeOfTask typeOfTask, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.typeOfTask = typeOfTask; //has potential to create error
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }


    /**
     * Changes the instance variable isDone to the
     * value provided
     *
     * @param isDone The value to which the instance
     *               variable property is to be updated to
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return getIsDoneString() + description;
    }

    /**
     * Returns a string which is used to display
     * the completion status of the current task
     * (calculated by using the isDone property of the object)
     * in a visually intuitive manner.
     * <p>
     *     A mark box with the cross signifies if the task is completed.
     *     An empty box symbolises a Task which has not been completed yet
     * </p>
     *
     *
     * @return A string showing if the task is completed or not.
     *
     */
    public String getIsDoneString() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns a character signifying the type of task.
     * It is implemented in all classes that inherit this
     * class (Task) and consequently the appropriate
     * character is returned.
     *
     * @return A character signifying the type of task
     */
    public abstract char getTaskCharacter();

    /**
     * Returns the current state of the object
     * in a format which can be directly written to a file
     *
     * @return a string which is in a storable format
     */
    public abstract String getStorableString();
}
