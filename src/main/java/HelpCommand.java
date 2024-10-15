package main.java;

/**
 * Represents a command to display help information to the user.
 */
public class HelpCommand extends Command {

    /**
     * Performs the help command by displaying all the valid commands the user can enter.
     *
     * @param tasks The task list that the command will work on.
     * @param ui The UI component of the chat bot.
     * @param storage The storage component of the chat bot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
