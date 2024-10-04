package bob.command;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

/**
 * Represents an abstract command that can be executed in the task management application.
 * Subclasses of Command must implement the execute method to perform specific actions.
 */
public abstract class Command {

    /**
     * Executes the command with the provided task list, user interface and storage.
     * Each subclass of Command will define the specific behaviour when executed.
     *
     * @param tasks   The TaskList that the command will operate on.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to handle saving of tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines whether the command will exit the program.
     * Each subclass will define whether the command is an exit command or not.
     *
     * @return true if the command exits the program, false otherwise.
     */
    public abstract boolean isExit();
}

