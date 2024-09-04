public class Task {
    protected String task;
    protected boolean done;
    protected static int length = 0;

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
        length++;
    }

    public Task(String task) {
        this.task = task;
        this.done = false;
        length++;
    }

    public Task() {
        this.task = "";
        this.done = false;
    }

    public String getDoneMarker() {
        String doneMarker = "";
        if (this.done) {
            doneMarker = "[X]";
        }
        else {
            doneMarker = "[ ]";
        }
        return doneMarker;
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
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void print() {
        System.out.println(getDoneMarker() + " " + this.task);
    }
}
