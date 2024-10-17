package niwa.command;

import niwa.Niwa;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;

import java.util.ArrayList;

/**
 * The {@code ByeCommand} class handles ending the chat session.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_GUIDE = "bye: End the chat.";
    public static final String[] COMMAND_KEYWORDS = {};

    private Niwa chatbot; // Instance of the Niwa chatbot

    public ByeCommand(Niwa chatbot) {
        setChatbot(chatbot); // Set the chatbot instance
    }

    public void setChatbot(Niwa chatbot) {
        this.chatbot = chatbot; // Assign chatbot
    }

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length; // Validate argument count
    }

    /**
     * Executes the bye command to end the chat session.
     *
     * @return A {@code CommandResult} containing the farewell message.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        chatbot.setRunning(false); // End the chatbot session
        messages.add(NiwaMesssages.BYE_MESSAGE); // Add farewell message

        return new CommandResult(messages); // Return results
    }
}
