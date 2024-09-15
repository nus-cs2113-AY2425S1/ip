package erika.task;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskArraySize = 0;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
        taskArraySize++;
    }

    @Override
    public void finalize() {
        taskArraySize--;
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

    public static int getTaskArraySize() {
        return taskArraySize;
    }

    public static void setTaskArraySize(int size) {
        taskArraySize = size;
    }

    public static void incrementTaskArraySize() {
        taskArraySize++;
    }

    public static void decrementTaskArraySize() {
        taskArraySize--;
    }
}
