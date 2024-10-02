package atom;

public class InvalidCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showInvalidCommandMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
