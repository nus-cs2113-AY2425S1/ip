package esme.command;

import esme.ui.Ui;


/**
 * An abstract class representing a command that can be executed by the application.
 * Subclasses should override the run() method to provide the implementation of the
 * command.
 */
public abstract class Command {
    /**
     * The user interface object that the command will interact with.
     */
    protected Ui ui;
    
    /**
     * Creates a new command with the given user interface object.
     * 
     * @param ui The user interface object to interact with.
     */
    public Command(Ui ui) {
        this.ui = ui;
    }

    /**
     * Executes the command.
     */
    public abstract void run();
}

