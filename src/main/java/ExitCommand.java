public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Display the exit message to the user
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        // Indicate that this command will exit the application
        return true;
    }
}
