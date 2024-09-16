package melchizedek.task;

public abstract class Task {
    private int id;
    private String description;
    private boolean isMarkAsDone;

    public Task() {
        this.isMarkAsDone = false;
    }

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isMarkAsDone = false;
    }

    public void markTaskAsDone() {
        isMarkAsDone = true;
    }

    public void unmarkTaskAsDone() {
        isMarkAsDone = false;
    }

    public String getStatusMark(){
        if (isMarkAsDone) {
            return "X";
        }
        return " ";
    }

    public String taskToFile() {
        if (isMarkAsDone) {
            return "1 | " + description;
        }
        return "0 | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusMark() + "] " + description;
    }
}
