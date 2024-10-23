package mong.task;

public abstract class Task implements TaskManager {
    private String description;
    private boolean isCompleted;
    private int index;
    private static int currentIndex = 0;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex() {
        this.index = currentIndex;
        currentIndex++;
    }

    @Override
    public String toFileFormat() {
        return description;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + description;
        }
        return "[ ] "+ description;
    }

    /**
     * Constructor of a Task.
     * @param description The name of the task (e.g. chinese class) in String format.
     * Default value of isCompleted for each task is false.
     */
    public Task(String description) {
        setDescription(description);
        this.isCompleted = false;
        setIndex();
    }
}
