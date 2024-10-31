/**
 * The Event class represents a task that spans a specific duration of time.
 * It is a subclass of Deadline, with additional information for the start and end times.
 */
public class Event extends Deadline {
    protected String deadlineTill;

    /**
     * Constructs a new Event task by parsing the input to extract the task name, start time, and end time.
     * The input should follow the format "{taskName} /from {Time} /to {Time}".
     *
     * @param input The input string containing the task name, start time, and end time.
     * @throws FenixException if the task name is blank or the input format is invalid.
     * @throws IllegalArgumentException if the input does not include both start and end times.
     */
    public Event(String input) throws FenixException {
        super(input);
        if (this.inputArray.length < 3) {
            throw new IllegalArgumentException("Task input must be in the format '{taskName} /from {Time} /to {Time}'");
        }
        this.deadlineTill = getDeadlineTill(inputArray);
    }

    /**
     * Constructs a new Event task with the specified completion status and formatted task information.
     *
     * @param isDone           Boolean value indicating whether the task is done.
     * @param formattedTaskInfo A string representing the formatted task information.
     */
    public Event(boolean isDone, String formattedTaskInfo) {
        super(isDone, formattedTaskInfo);
    }

    /**
     * Extracts the end time ("/to {Time}") from the input array.
     *
     * @param inputArray The array containing the task name, start time, and end time.
     * @return The end time as a string.
     */
    public String getDeadlineTill(String[] inputArray) {
        return inputArray[2];
    }

    /**
     * Returns the string representation of the Event task, including its type, completion status,
     * task name, start time, and end time.
     * The format includes a "[E]" to indicate the task is an Event, followed by a "[X]" if the task is done,
     * or "[ ]" if it is not done, along with the start and end times.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        String taskStatus = "[E][" + (isDone ? "X" : " ") + "] ";
        if (formattedTaskInfo.isBlank()) {
            String from = " (from: " + deadline.replaceFirst("^from\\s+", "");
            String to = "to: " + deadlineTill.replaceFirst("^to\\s+", "") + ")";
            return taskStatus + taskName + from + to;
        }
        return taskStatus + formattedTaskInfo;
    }
}
