package melchizedek.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean isMarkAsDone() {
        return isDone;
    }

    public void markTaskAsDone() {
        isDone = true;
    }

    public void unmarkTaskAsDone() {
        isDone = false;
    }

    public String getStatusMark(){
        if (isDone) {
            return "X";
        }
        return " ";
    }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    public String taskToFile() {
        if (isDone) {
            return "1 | " + description;
        }
        return "0 | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusMark() + "] " + description;
    }
}
