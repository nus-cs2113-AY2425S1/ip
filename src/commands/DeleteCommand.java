package commands;

import exception.IncompleteCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import static main.Sirius.*;

/**
 * The DeleteCommand class handles the deletion of a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the delete command, which removes a task from the task list.
     * It first parses the user input to get the task number, deletes the task from the list,
     * and then updates the storage and UI.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IncompleteCommandException {
        String[] slashCommand = userInput.split(SLASH);
        String commandContent = slashCommand[0]; //commandContent = commandPrefix + taskName
        String commandPrefix = commandContent.split(SPACE)[0];
        String taskName = commandContent.replace(commandPrefix, EMPTY).trim();

        try {
            if (taskName.isEmpty()) { // input is only mark or unmark
                throw new IncompleteCommandException("Task index");
            }
            int taskNumber = Integer.parseInt(taskName);
            if (taskNumber > tasks.getListSize()) {
                throw new IndexOutOfBoundsException();
            }
            ui.showTaskDeleted(tasks, taskNumber);
            tasks.deleteTask(taskNumber);
            ui.showCurrentSizeOfList(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexOutOfBoundError();
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        } catch (IncompleteCommandException e){
            ui.showLine();
            ui.print(e.getMessage());
            ui.showLine();
        }
        storage.saveTaskList(tasks.getList(), ui);
    }
}
