package commands;

import exception.IncompleteCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import static main.Sirius.*;

public class DeleteCommand extends Command {
    private final String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

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
            // 自然报错outOfBound和numberFormat?
            ui.showTaskDeleted(tasks, tasks.getListSize(), taskNumber);
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
