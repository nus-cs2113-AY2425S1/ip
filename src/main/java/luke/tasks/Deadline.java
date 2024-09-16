package luke.tasks;

public class Deadline extends Task{
    protected String by;


    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline() {
        super();
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.isDone ? "X" : " ", this.description, by);
    }
    public String toString2() {
        return String.format("D|%d|%s|%s", isDone ? 1 : 0, description, by);
    }
}
