package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;


/**
 * Represents a command that is invalid or unrecognized by the application.
 * The <code>InvalidCommand</code> is used when the user input does not match any known command.
 */
public class InvalidCommand extends Command {

    /**
     * Constructs an InvalidCommand with default settings.
     */
    public InvalidCommand() {
        super();
    }

    /**
     * Executes the InvalidCommand, displaying an error message indicating that the user input was not recognized.
     *
     * @param tasks   The TaskList containing the tasks (unused in this command).
     * @param ui      The Ui for user interaction (unused in this command).
     * @param storage The Storage object for handling task storage (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        System.out.print("eh can you give me one of the inputs i specified above...");
    }
}
