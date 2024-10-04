package ellio.command;

import ellio.BotText;
import ellio.EllioExceptions;
import ellio.storage.Storage;
import ellio.task.TaskList;
import ellio.ui.Ui;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Extract the task from the list and marks it as incomplete
     * Updates both the list and the save File
     * @param tasks Input by user
     * @param ui Ui interface reference
     * @param storage Reference for Storage functions
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EllioExceptions{
        int index = Integer.parseInt(tasks.getTaskIndex(inputCommand));
        if(index > tasks.getNumberTask()){
            throw new EllioExceptions.OutOfIndexException(tasks.getNumberTask());
        }
        tasks.getTask(index-1).unmarkTaskAsDone();
        storage.updateSavedTaskFile();
        ui.showUnmarkMessage(tasks.getTask(index-1));
    }
}
