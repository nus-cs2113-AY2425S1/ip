public class Deadline extends Task {

    protected String by;

    public Deadline(String description) {
        super(description);
    }
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }



    @Override
    public String toString() {
        String output = "[D]" + super.toString();
        if (!by.isEmpty()) {
            output += " (by: " + by + ")";
        }
        return output;
    }
}
