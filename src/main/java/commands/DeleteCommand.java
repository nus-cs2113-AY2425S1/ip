package commands;

import exception.BentoException;
import exception.InvalidIndexException;
import exception.MissingTaskException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.DELETE_COMMAND;

public class DeleteCommand extends Command {
    private String userInput;

    public DeleteCommand(String userInput) {
        super(DELETE_COMMAND);
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException, MissingTaskException, SaveFileErrorException {
        try {
            userInput = parser.removeDeletePrefix(userInput);
            int index = Integer.parseInt(userInput) - 1;
            Task task = tasks.retrieveTask(index);
            tasks.deleteTask(index);
            saveTask(storage, tasks, ui);
            ui.printDeleteTaskSuccessMessage(task, tasks);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException();
        }
    }


}
