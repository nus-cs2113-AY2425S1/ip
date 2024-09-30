package Commands;

/**
 * Represents an incorrect command that provides feedback to the user.
 * This class extends the {@code Command} class and is used to handle
 * situations where the user's input does not match any known commands.
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

}