public class Event extends Deadline {
    protected String deadlineTill;

    public Event(String input) throws FenixException {
        super(input);
        if (this.inputArray.length < 3) {
            throw new IllegalArgumentException("Task input must be in the format '{taskName} /from {Time} /to {Time}'");
        }
        this.deadlineTill = getDeadlineTill(inputArray);
    }

    public String getDeadlineTill(String[] inputArray) {
        return inputArray[2];
    }

    @Override
    public String toString() {
        String taskStatus = "[E][" + (isDone ? "X" : " ") + "] ";
        String from = " (from: " + deadline.replaceFirst("^from\\s+", "");
        String to = "to: " + deadlineTill.replaceFirst("^to\\s+", "") + ")";
        return taskStatus + taskName + from + to;
    }
}
