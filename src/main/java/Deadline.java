public class Deadline extends Task {
    protected String deadline;
    protected String[] inputArray;

    public Deadline(String input) throws FenixException {
        super();
        splitInput(input);
        // Assign the taskname
        this.taskName = getTaskName(inputArray);
        // Assign the deadline
        this.deadline = getDeadline(inputArray);
        //System.out.println(deadlineBy);
    }

    public Deadline(boolean isDone, String formattedTaskInfo) {
        super(isDone, formattedTaskInfo);
    }

    // Combined helper method to extract either taskName or deadline
    private void splitInput(String input) {
        this.inputArray = input.split("/");
        // Validate input length
        if (this.inputArray.length < 2) {
            throw new IllegalArgumentException("Task input must be in the format '{taskName} /by {Time}'");
        }
    }

    public String getTaskName(String[] inputArray) throws FenixException {
        if (inputArray[0].isBlank()) {
            throw new FenixException("Task name cannot be blank");
        }
        return inputArray[0];
    }

    public String getDeadline(String[] inputArray) {
        return inputArray[1];
    }

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
