package commands;

import exception.BentoException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.TaskList;
import ui.Parser;
import ui.Ui;

/**
 * Abstract class representing a generic command in Bento.
 * This class serves as a blueprint for all commands that Bento can execute.
 * Subclasses are expected to provide command-specific implementations.
 */
public abstract class Command {
    protected Parser parser;
    protected boolean isExit = false;
    private String commandPrefix;

    /**
     * Constructs a Command with the specified command prefix.
     *
     * @param commandPrefix The prefix associated with this command.
     */
    public Command(String commandPrefix) {
        this.commandPrefix = commandPrefix;
        this.parser = new Parser();
    }

    /**
     * Retrieves the command prefix.
     *
     * @return The command prefix.
     */
    public String getCommandPrefix() {
        return commandPrefix;
    }

    /**
     * Sets a new command prefix.
     *
     * @param commandPrefix The new command prefix.
     */
    public void setCommandPrefix(String commandPrefix) {
        this.commandPrefix = commandPrefix;
    }

    /**
     * Checks if the command signals an exit from the chatbot.
     *
     * @return True if this command indicates an exit; false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets the exit status for this command.
     *
     * @param exit The exit status to set.
     */
    public void setExit(boolean exit) {
        isExit = exit;
    }

    /**
     * Saves the current task list quietly without user feedback.
     *
     * @param storage The storage object to handle file operations.
     * @param tasks The current task list to save.
     * @param ui The user interface to interact with the user.
     * @throws SaveFileErrorException If an error occurs while saving the task list.
     */
    public void saveTask(Storage storage, TaskList tasks, Ui ui) throws SaveFileErrorException {
        storage.saveTaskListQuiet(tasks, ui, parser);
    }

    /**
     * Saves the current task list with user feedback.
     *
     * @param storage The storage object to handle file operations.
     * @param tasks The current task list to save.
     * @param ui The user interface to interact with the user.
     * @throws SaveFileErrorException If an error occurs while saving the task list.
     */
    public void saveTaskList(Storage storage, TaskList tasks, Ui ui) throws SaveFileErrorException {
        storage.saveTaskList(tasks, ui, parser);
    }

    /**
     * Abstract method to execute the command.
     * Subclasses must provide their own implementation for how the command should be executed.
     *
     * @param tasks The current task list.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to handle file operations.
     * @throws BentoException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BentoException;
}
