public class Event extends Deadline {
    protected String deadlineTill;

    public Event(String input) {
        super(input);
        if (this.inputArray.length < 3) {
            throw new IllegalArgumentException("Length issue");
        }
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
