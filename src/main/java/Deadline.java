/**
 * The Deadline class represents a task that has a deadline.
 * It is a subclass of Task and includes additional information about when the task is due.
 */
public class Deadline extends Task {
    protected String deadline;
    protected String[] inputArray;

    /**
     * Constructs a new Deadline task by parsing the input to extract the task name and deadline.
     * The input should follow the format "{taskName} /by {Time}".
     *
     * @param input The input string containing the task name and deadline.
     * @throws FenixException if the task name is blank or the input format is invalid.
     */
    public Deadline(String input) throws FenixException {
        super();
        splitInput(input);
        // Assign the task name
        this.taskName = getTaskName(inputArray);
        // Assign the deadline
        this.deadline = getDeadline(inputArray);
    }

    /**
     * Constructs a new Deadline task with the specified completion status and formatted task information.
     *
     * @param isDone           Boolean value indicating whether the task is done.
     * @param formattedTaskInfo A string representing the formatted task information.
     */
    public Deadline(boolean isDone, String formattedTaskInfo) {
        super(isDone, formattedTaskInfo);
    }

    /**
     * Splits the input string into components: the task name and the deadline.
     * The input must be in the format "{taskName} /by {Time}".
     *
     * @param input The input string containing the task name and deadline.
     * @throws IllegalArgumentException if the input format is invalid.
     */
    private void splitInput(String input) {
        this.inputArray = input.split("/");
        // Validate input length
        if (this.inputArray.length < 2) {
            throw new IllegalArgumentException("Task input must be in the format '{taskName} /by {Time}'");
        }
    }

    /**
     * Extracts the task name from the input array.
     *
     * @param inputArray The array containing the task name and deadline.
     * @return The task name.
     * @throws FenixException if the task name is blank.
     */
    public String getTaskName(String[] inputArray) throws FenixException {
        if (inputArray[0].isBlank()) {
            throw new FenixException("Task name cannot be blank");
        }
        return inputArray[0];
    }

    /**
     * Extracts the deadline from the input array.
     *
     * @param inputArray The array containing the task name and deadline.
     * @return The deadline as a string.
     */
    public String getDeadline(String[] inputArray) {
        return inputArray[1];
    }

    /**
     * Returns the string representation of the Deadline task, including its type, completion status,
     * task name, and deadline.
     * The format includes a "[D]" to indicate the task is a Deadline, followed by a "[X]" if the task is done,
     * or "[ ]" if it is not done, and the deadline information.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        String taskStatus = "[D][" + (isDone ? "X" : " ") + "] ";
        if (formattedTaskInfo.isBlank()) {
            String deadlineInfo = " (by: " + deadline.replaceFirst("^by\\s+", "") + ")";
            return taskStatus + taskName + deadlineInfo;
        }
        return taskStatus + formattedTaskInfo;
    }
}
