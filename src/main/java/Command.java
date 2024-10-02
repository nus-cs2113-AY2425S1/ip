public abstract class Command {
    // Abstract method to execute the command
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AirBorderException;

    // Method to check if the command signals an exit
    public boolean isExit() {
        return false;
    }
}
