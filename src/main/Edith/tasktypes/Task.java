package tasktypes;

/**
 *
 */
abstract public class Task {
    private final String description;
    private boolean isDone;
    public TypeOfTask typeOfTask;

    /**
     * @param description
     * @param typeOfTask
     */
    public Task(String description, TypeOfTask typeOfTask) {
        this.description = description;
        this.isDone = false;
        this.typeOfTask = typeOfTask; //has potential to create error
    }

    /**
     * @param description
     * @param typeOfTask
     * @param isDone
     */
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

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return getIsDoneString() + description;
    }

    /**
     * @return
     */
    public String getIsDoneString() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * @return
     */
    public abstract char getTaskCharacter();

    /**
     * @return
     */
    public abstract String getStorableString();
}
