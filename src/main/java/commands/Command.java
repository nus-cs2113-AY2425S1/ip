package commands;

import exceptions.InvalidCommandException;
import taskmanager.taskManager;

/**
 * The Command class is an abstract class that represents a command issued by the user.
 * Each command must define its own execute method, which operates on the provided Storage.
 */

public abstract class Command {

    /**
     * Executes the command with the given storage. Each subclass of Command
     * must implement this method to define the behavior of the command.
     *
     * @param storage The storage object that the command will interact with.
     * @throws InvalidCommandException If the command is invalid or cannot be executed properly.
     */

    public abstract void execute(taskManager storage) throws InvalidCommandException;
}
