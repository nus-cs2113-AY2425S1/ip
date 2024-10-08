package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

/**
 * Represents a command to exit the application.
 * This command allows the user to terminate the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showMsg("Bye. Hope to see you again soon! Keep Grokking :)");
        ui.showLine();
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Error saving tasks to file before exiting.");
        }
    }

    /**
     * Indicates that this command will terminate the program.
     * @return true as the exit command terminates the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
