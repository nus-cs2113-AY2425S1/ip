public class Event extends Deadline {
    protected String deadlineTill;

    public Event(String input) {
        super(input);
        this.deadlineTill = getDeadlineTill(inputArray);
    }

    public String getDeadlineTill(String[] inputArray) {
        return inputArray[2];
    }

    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + taskName + " (from: " + deadline.replaceFirst("^from\\s+",
                "") +
                "to: " + deadlineTill.replaceFirst("^to\\s+", "") + ")";
    }
}
