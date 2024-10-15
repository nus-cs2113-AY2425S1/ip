package main.java;

/**
 * Represents an abstract command that can be performed with the task management chat bot.
 * Specific commands like add or delete will extend this class.
 */
public abstract class Command {

    /**
     * Performs the command using the given task list, UI and storage components.
     *
     * @param tasks The task list that the command will work on.
     * @param ui The UI component of the chat bot.
     * @param storage The storage component of the chat bot.
     * @throws KenChatException If there is an error when performing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KenChatException;

    /**
     * Notifying the chat bat on its running status after performing the command.
     * All commands will return true, except only when ExitCommand is called.
     *
     * @return true If the chat bot should continue running.
     */
    public boolean isRunning() {
        return true;
    }
}
