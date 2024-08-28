public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setStatus(boolean newStatus) {
        String completion = newStatus ? "X" : " ";

        if (newStatus == this.isDone) {
            System.out.println("Oops, the task '" + this.name + "' has already been marked as "
                + "[" + completion + "]!");
            return;
        }

        if (newStatus) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println("  [" + completion + "] " + this.name);
        this.isDone = newStatus;
    }
}
