package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;

import java.util.ArrayList;

public class EchoCommand extends Command {
    public static final String COMMAND_WORD = "echo";
    public static final String COMMAND_GUIDE = "echo [string]: Echo the string.";
    public static final String[] COMMAND_KEYWORDS = {""};

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
     * Echo a String.
     *
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        ArrayList<String> messages = new ArrayList<>();

        String inputString = arguments.get(COMMAND_KEYWORDS[0]);
        messages.add(inputString);

        return new CommandResult(messages);
    }

}
