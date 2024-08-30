public class Task {
    private String description;
    private boolean isDone;
    private static int taskSize = 0;
    public Task (String description) {
        this.description = description;
        this.isDone = false;
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

    public static int getTaskSize() {
        return taskSize;
    }

    public static void setTaskSize(int size) {
        taskSize = size;
    }

    public static void incrementTaskSize() {
        taskSize++;
    }
}
