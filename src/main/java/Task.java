public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + "[" + this.getStatusIcon() + "] " + this.description);

        System.out.println("____________________________________________________________");
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + "[" + this.getStatusIcon() + "] " + this.description);

        System.out.println("____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}