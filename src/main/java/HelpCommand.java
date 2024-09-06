import java.util.List;

public class HelpCommand extends Command {
    protected List<Command> commands;

    public HelpCommand() {
        setFormat("");
        setWord("help");
        setGuide("help: Print this guide.");
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String[] convertArguments(String command) {
        if (!command.isEmpty()) {
            return null;
        }
        return new String[0];
    }

    /**
     * Print a user guide.
     *
     * @param rawArgumentString should be null.
     */
    @Override
    public void execute(String rawArgumentString) {
        super.execute(rawArgumentString);
        for (Command command : commands) {
            System.out.printf(PREFIX + command.getGuide() + "\n");
        }
    }

}
