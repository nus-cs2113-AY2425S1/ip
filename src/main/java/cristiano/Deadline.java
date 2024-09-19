package cristiano;

public class Deadline extends Goal {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    public static Deadline fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        String description = parts[2];
        String by = parts[3];
        Deadline deadline = new Deadline(description, by);
        if (parts[1].equals("1")) {
            deadline.isDone = true;
        }
        return deadline;
    }
}