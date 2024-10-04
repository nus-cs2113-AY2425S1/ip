package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true; // Indicates this command should exit the application
    }
}
