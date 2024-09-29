package commands;

public class InvalidCommand extends Command {
    boolean isExit;

    public InvalidCommand() {
        this.isExit = false;
    }

    @Override
    public String execute() {
        return "Invalid command :(";
    }
}
