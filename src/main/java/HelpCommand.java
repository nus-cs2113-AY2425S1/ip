/**
 * Represents a command to display help information to the user.
 * When executed, this command shows a list of available commands.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command by showing a list of available commands.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The UI object to display the help message.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp(); // Show help menu
    }
}