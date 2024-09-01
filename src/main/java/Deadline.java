public class Deadline extends Task {

    protected String by;

    //Deadline Constructor inherits Task
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    //Override toString method to show marker
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}