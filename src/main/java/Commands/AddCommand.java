package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;

public abstract class AddCommand extends Command {

    protected String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    // Each subclass will implement its specific execute logic
    public abstract void execute(Storage storage) throws InvalidCommandException;
}
