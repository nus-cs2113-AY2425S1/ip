package commands;

import exceptions.InvalidCommandException;
import taskmanager.taskManager;

/**
 * The AddCommand class is an abstract class that represents a command for adding tasks.
 * It stores the user input and provides an abstract execute method that must be implemented
 * by its subclasses to define the behavior of adding different types of tasks.
 */

public abstract class AddCommand extends Command {

    protected final String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the specific add command logic. Each subclass of AddCommand
     * must implement this method to define how the command interacts with Storage.
     *
     * @param storage The storage object that the command will interact with.
     * @throws InvalidCommandException If the command is invalid or cannot be executed properly.
     */
    
    public abstract void execute(taskManager storage) throws InvalidCommandException;
}
