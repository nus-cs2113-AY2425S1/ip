package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

/**
 * Represents an abstract command that can be executed.
 * A command can either exit the program or perform actions based on specific instructions.
 */
public abstract class Command {

    protected boolean isExit = false;
    protected String instruction;

    /**
     * Constructs a Command with no specific instructions.
     */
    public Command() {
        this.instruction = "";
    }

    /**
     * Constructs a Command with the specified instructions.
     *
     * @param instruction The instructions for the command.
     */
    public Command(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface for output messages.
     * @param storage The storage system for saving tasks.
     * @throws AlyException If any errors occur during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AlyException;

    /**
     * Sets the command to exit after execution.
     */
    protected void setExit() {
        this.isExit = true;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command will exit the program, false otherwise.
     */
    public boolean hasExited() {
        return isExit;
    }
}