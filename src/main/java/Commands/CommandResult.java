package Commands;

public class CommandResult {

    private String feedback;

    public CommandResult(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }
}