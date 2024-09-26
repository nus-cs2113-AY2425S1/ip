package bosco.command;

public class ExitCommand extends Command {
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
