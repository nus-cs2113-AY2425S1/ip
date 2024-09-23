package commands;

import exception.SaveFileErrorException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.BYE_COMMAND;

/**
 * Represents a command that allows the user to exit Bento.
 * This class extends {@link Command} and handles the execution of the bye command,
 * including saving tasks and displaying a farewell message to the user.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand, initializing it with the bye command prefix.
     */
    public ByeCommand() {
        super(BYE_COMMAND);
    }

    /**
     * Executes the bye command, saving the task list and displaying a farewell message.
     *
     * @param tasks The current task list.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to handle file operations.
     * @throws SaveFileErrorException If an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveFileErrorException {
        saveTaskList(storage, tasks, ui);
        ui.saySayonara();
        setExit(true);
    }
}
