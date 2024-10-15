package main.java;

/**
 * Represents a command to exit the chat bot.
 */
public class ExitCommand extends Command {

    /**
     * Performs the exit command. No other action is required.
     *
     * @param tasks The task list that the command will work on.
     * @param ui The UI component of the chat bot.
     * @param storage The storage component of the chat bot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    /**
     * Notify the chat bot to terminate.
     *
     * @return false so that the chat bot will stop running.
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
