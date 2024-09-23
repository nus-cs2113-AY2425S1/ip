package niwa.command;

import niwa.Niwa;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;

import java.util.ArrayList;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_GUIDE = "bye: End the chat.";
    public static final String[] COMMAND_KEYWORDS = {};
    Niwa chatbot;

    public ByeCommand(Niwa chatbot) {
        setChatbot(chatbot);
    }

    public void setChatbot(Niwa chatbot) {
        this.chatbot = chatbot;
    }

    public boolean isValidArguments() {
        if (arguments.size() != COMMAND_KEYWORDS.length) {
            return false;
        }
        for (String keyword: COMMAND_KEYWORDS) {
            if (!arguments.containsKey(keyword)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Prints a farewell message and inactivate niwa.Niwa chatbot.
     *
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        ArrayList<String> messages = new ArrayList<>();

        chatbot.setRunning(false);
        messages.add(NiwaMesssages.BYE_MESSAGE);

        return new CommandResult(messages);
    }

}
