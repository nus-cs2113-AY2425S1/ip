package nell.tasks;

public class Deadline extends nell.tasks.Task {
    private String by;

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.by);
    }
}
