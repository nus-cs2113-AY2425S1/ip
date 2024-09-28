package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand and sets the exit flag.
     */
    public ExitCommand() {
        super();
        this.setExit();
    }

    /**
     * Executes the exit command by writing tasks to storage and printing a goodbye message.
     *
     * @param taskList The task list to save before exiting.
     * @param ui Unused parameter.
     * @param storage The storage system to save the tasks.
     * @throws AlyException If any error occurs during storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        storage.write(taskList);
        System.out.println("Bye! Time for MacDonalds!");
    }
}