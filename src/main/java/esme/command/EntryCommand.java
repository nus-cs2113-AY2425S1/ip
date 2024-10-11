package esme.command;

import esme.ui.Ui;

/**
 * Represents the command that handles the user's input when adding a task.
 * The command and input should be in the format required for the corresponding task type.
 */
public class EntryCommand extends Command {
    private final String command;
    private final String input;

    /**
     * Creates an EntryCommand with the given command and input.
     * 
     * @param ui The user interface to use for displaying messages to the user.
     * @param command The command to use when adding the task.
     * @param input The input to process when adding the task.
     */
    public EntryCommand(Ui ui, String command, String input) {
        super(ui);
        this.command = command;
        this.input = input;
    }

    /**
     * Executes the command by adding a task to the task list based on the given command and input.
     */
    @Override
    public void run() {
        super.ui.addTaskToList(command, input);
    }
}
