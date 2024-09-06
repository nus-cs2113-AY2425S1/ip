public class Deadline extends Todo {
    protected String deadline;
    protected String[] inputArray;

    public Deadline(String input) {
        // Call the parent constructor with the task name
        super();
        splitInput(input);
        // Assign the taskname
        this.taskName = getTaskName(inputArray);
        // Assign the deadline
        this.deadline = getDeadline(inputArray);
        //System.out.println(deadlineBy);
    }

    // Combined helper method to extract either taskName or deadline
    private void splitInput(String input) {
        this.inputArray = input.split("/");
        // Validate input length
        if (this.inputArray.length < 2) {
            throw new IllegalArgumentException("Input must be in the format 'taskName/deadline'");
        }
    }

    public String getTaskName(String[] inputArray) {
        return inputArray[0];
    }

    public String getDeadline(String[] inputArray) {
        return inputArray[1];
    }

    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + taskName + " (by: " + deadline.replaceFirst("^by\\s+", "") + ")";
    }
}
