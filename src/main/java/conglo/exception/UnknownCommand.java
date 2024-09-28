package conglo.exception;

public class UnknownCommand extends CongloException {
    public UnknownCommand() {
        super("Sorry, I'm not smart enough to understand what you mean.");
    }
}
