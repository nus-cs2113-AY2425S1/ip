package atom.exception;

public class TaskIdOutOfBoundsException extends RuntimeException {

    protected int taskCount;
    protected String errorMessage;

    public TaskIdOutOfBoundsException(int taskCount) {
        this.taskCount = taskCount;
        errorMessage = this.getMessage();
    }

    @Override
    public String getMessage() {
        String message = "Invalid task id!!" +
                "\nYou currently have "  + taskCount + " tasks in the list.\n" +
                "\n-> Use the \"list\" command to view your current tasks.";

        return message;
    }
}
