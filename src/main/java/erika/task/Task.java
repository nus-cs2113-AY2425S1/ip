package erika.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }

    public String generateFileLine() {
        return "";
    }

    public void setMark(boolean mark) {
        this.isDone = mark;
    }

    public boolean getMark() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
