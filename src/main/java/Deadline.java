public class Deadline extends Task {

    protected String dueDate;

    //Deadline Constructor inherits Task
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    //Override toString method to show marker
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}