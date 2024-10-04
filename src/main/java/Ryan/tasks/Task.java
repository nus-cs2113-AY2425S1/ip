package Ryan.tasks;

public class Task {

    public static final String MARKED_SYMBOL = "[X]";
    public static final String UNMARKED_SYMBOL = "[ ]";
    public static final int MARKED_VALUE = 1;
    public static final int UNMARKED_VALUE = 0;
    public static final String DEFAULT_TASK_TYPE = "T";

    private final String description;
    private boolean isMarked = false;

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    public String getDescription() {
        return this.description;
    }

    public String toFile() {
        return String.format("%s | %d | %s", getTaskType(), isMarked() ? MARKED_VALUE : UNMARKED_VALUE, getDescription());
    }

    public String getTaskType() {
        return DEFAULT_TASK_TYPE;
    }

    @Override
    public String toString() {
        return (isMarked ? MARKED_SYMBOL : UNMARKED_SYMBOL) + " " + description;
    }
}
