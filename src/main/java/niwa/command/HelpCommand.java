package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;

import java.util.List;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_GUIDE = "help: Print this guide.";
    public static final String[] COMMAND_KEYWORDS = {};
    protected List<Command> commands;

    public void setCommands(List<Command> commands) {
        this.commands = commands;
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
     * Print a user guide.
     *
     */
    @Override
    public void execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }
        for (Command command : commands) {
            try {
                String guide = (String) command.getClass().getField("COMMAND_GUIDE").get(null);
                System.out.printf(PREFIX + guide + "\n");
            } catch (IllegalAccessException | NoSuchFieldException e) {
                System.out.printf("Invalid command: " + e.getMessage());
            }

        }
    }

}
