package commands;
import exception.IncompleteCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import static main.Sirius.*;

/**
 * The ListCommand class handles the "mark" and "unmark" command, which marks a task in the list as done or unmarks it.
 */
public class MarkCommand extends Command {
    private final String userInput;
    private final boolean isMarked;
    public MarkCommand(String userInput, boolean isMarked) {
        this.userInput = userInput;
        this.isMarked = isMarked;
    }

    /**
     * Executes the mark or unmark command by marking/unmarking the task as complete/incomplete.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IncompleteCommandException {
        String[] slashCommand = userInput.split(SLASH);
        String commandContent = slashCommand[0]; //commandContent = commandPrefix + taskName
        String commandPrefix = commandContent.split(SPACE)[0];
        String taskName = commandContent.replace(commandPrefix, EMPTY).trim();


        try{
            if (taskName.isEmpty()) { // input is only mark or unmark
                throw new IncompleteCommandException("Task index");
            }  // note that write in the try block to be caught
            int taskNumber = Integer.parseInt(taskName);
            if (isMarked) {
                tasks.markTask(taskNumber, true);
                ui.showTaskMarked(tasks, taskNumber);
            } else {
                tasks.markTask(taskNumber, false);
                ui.showTaskUnmarked(tasks, taskNumber);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexOutOfBoundError();
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        } catch (IncompleteCommandException e){
            ui.showIncompleteCommandError(e);
        }
        storage.saveTaskList(tasks.getList(), ui);
    }
}
