package quinn.task;

public abstract class Task {
    private final TaskType type;
    private final String description;
    private boolean isDone;

    public Task(TaskType type, String description) {
        // By default, the task is not done
        this(type, description, false);
    }

    public Task(TaskType type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "[✔]"; // mark done task with ✔
        } else {
            return "[ ]";
        }
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public boolean hasKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return "[" + type.getAbbreviation() + "] " + getStatusIcon() + " " + description;
    }

    public String saveFormat() {
        return type.getAbbreviation() + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
