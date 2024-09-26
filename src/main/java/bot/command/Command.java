package bot.command;

import bot.Storage;
import bot.TobyBotException;
import bot.Ui;
import task.TaskList;

/**
 * Abstract class representing a command that can be executed by TobyBot.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to handle messages.
     * @param storage The storage handler to save/load tasks.
     * @throws TobyBotException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TobyBotException;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}