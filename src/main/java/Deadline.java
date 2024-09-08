public class Deadline extends Task{
    protected String by;


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
}
