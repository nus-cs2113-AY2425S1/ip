public class Deadline extends Task {
    // private final String TYPE = "D";
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // @Override
    // public String getType() {
    //     return TYPE;
    // }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
