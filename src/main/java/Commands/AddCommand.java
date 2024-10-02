package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;

/**
 * The AddCommand class is an abstract class that represents a command for adding tasks.
 * It stores the user input and provides an abstract execute method that must be implemented
 * by its subclasses to define the behavior of adding different types of tasks.
 */

public abstract class AddCommand extends Command {

    protected String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    // Each subclass will implement its specific execute logic
    public abstract void execute(Storage storage) throws InvalidCommandException;
}
