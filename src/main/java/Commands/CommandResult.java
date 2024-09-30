package Commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private String feedback;

    /**
     * Creates a new CommandResult with the specified feedback.
     *
     * @param feedback The feedback message to be shown to the user.
     */
    public CommandResult(String feedback) {
        this.feedback = feedback;
    }

    /**
     * Retrieves the feedback message from the CommandResult.
     *
     * @return The feedback message.
     */
    public String getFeedback() {
        return feedback;
    }
}