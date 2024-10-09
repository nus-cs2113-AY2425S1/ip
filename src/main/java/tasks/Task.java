package tasks;

/**
 * Task class acts as the base class for its child classes.
 * Stores the task description in the {@code task} and the
 * status of the task in the {@code isDone} boolean
 */
public class Task {
    protected String task;
    protected boolean isDone;
    /**
     * Constructor used when decoding tasks from storage.
     * @param task the task description
     * @param isDone the task status
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }
    /**
     * Default constructor for new Tasks
     * @param task the task description
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getDoneMarker() {
        if (this.isDone) {
            return "[X]";
        }
        return "[ ]";
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    public void print() {
        System.out.println(getDoneMarker() + " " + this.task);
    }
    /**
     * Formats tasks into a string object to be encoded onto a text file
     * @return String formatted for ease of decoding in the {@code TaskDecoder}
     */
    @Override
    public String toString() {
        return String.format("%b | %s", this.isDone, this.task);
    }
}
