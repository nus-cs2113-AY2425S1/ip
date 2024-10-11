public class Deadline extends Task {
    public Deadline(String description, String time) {
        super(description, time); // Store time
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + time;
    }
}