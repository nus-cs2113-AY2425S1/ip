package mong.task;

public abstract class Task {
    private String description;
    private boolean isCompleted;
    private int index;
    public static int currentIndex = 0;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Prints out command and the following 'mark' action that has been completed on it.
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
        if (isCompleted) {
            System.out.println("Mong >_<!!I have marked it as completed:");
            System.out.println(this);
        } else {
            System.out.println("Mong-mong :<! The task has been unmarked:");
            System.out.println(this);
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex() {
        this.index = currentIndex;
        currentIndex++;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + description;
        }
        return "[ ] "+ description;
    }

    public Task(String description) {
        setDescription(description);
        // default value for isCompleted is false
        this.isCompleted = false;
        setIndex();
    }
}
