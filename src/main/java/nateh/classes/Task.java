package nateh.classes;

public class Task {
    protected static int length = 0;
    protected String task;
    protected boolean isDone;

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
        length++;
    }

    public Task(String task) {
        this.task = task;
        this.isDone = false;
        length++;
    }

    public Task() {
        this.task = "";
        this.isDone = false;
    }

    public String getDoneMarker() {
        if (this.isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    public static int getLength() {
        return length;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void print() {
        System.out.println(getDoneMarker() + " " + this.task);
    }
    @Override
    public String toString() {
        return getDoneMarker() + " " + this.task;
    }
}
