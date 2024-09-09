package nell;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(),
                this.description, this.by);
    }
}
