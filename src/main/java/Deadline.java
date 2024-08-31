public class Deadline extends Task {
    protected String by;

    public Deadline(String input) {
        super(input.split("/", 2)[0].trim());

        String[] parts = input.split("/", 2);

        this.by = parts[1].trim().substring(3); // ignore "by "
        this.icon = "D";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (by: " + by + ")";
    }
}
