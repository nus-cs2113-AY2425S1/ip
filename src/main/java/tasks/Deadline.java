package tasks;

/**
 * Deadline class inherits from {@code Task} and represents tasks with deadlines
 */
public class Deadline extends Task {
    private String deadline;
    /**
     * Default constructor for new Tasks
     * @param task the task description
     * @param deadline is the deadline of the task
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Constructor used when decoding tasks from storage.
     * @param task the task description
     * @param isDone the task status
     * @param deadline is the deadline of the task
     */
    public Deadline(String task, boolean isDone, String deadline) {
        super(task, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }
    public String getTypeMarker() {
        return "[D]";
    }
    @Override
    public void print() {
        System.out.print(getTypeMarker());
        System.out.print(getDoneMarker() + " " + this.task);
        System.out.printf(" (by: %s)\n", this.deadline);
    }
    @Override
    public String toString() {
        return String.format("%s | %b | %s | %s", getTypeMarker(), this.isDone, this.task, this.deadline);
    }
}
