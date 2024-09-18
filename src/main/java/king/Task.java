package king;

public class Task {

    protected final String taskStartTime = "NIL";
    protected final String taskEndTime = "NIL";
    public static final String TASKTYPEICON = "[ ]";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    protected String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    protected String getTaskDescription() {
        return getStatusIcon() + description;
    }

    protected void markAsDone() {
        this.isDone = true;
        System.out.println("____________________________________________________________\n"
                           + "Nice! I've marked this task as done:\n" + getTaskDescription());

        System.out.println("____________________________________________________________");
    }

    protected void markAsUndone() {
        this.isDone = false;
        System.out.println("____________________________________________________________\n"
                           + "OK, I've marked this task as not done yet:\n" + getTaskDescription());

        System.out.println("____________________________________________________________");
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}