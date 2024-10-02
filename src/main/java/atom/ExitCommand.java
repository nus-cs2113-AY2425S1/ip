package atom;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.store(tasks);
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }


}
