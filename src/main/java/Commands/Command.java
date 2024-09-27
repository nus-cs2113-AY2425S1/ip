package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;

public abstract class Command {
    public abstract void execute(Storage storage) throws InvalidCommandException;
}
