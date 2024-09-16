package commands;

import exception.InvalidIndexException;
import exception.MissingTaskException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.MARK_COMMAND;

public class MarkCommand extends Command {
    private boolean toMark;
    private String taskIndex;

    public MarkCommand(boolean toMark, String taskIndex) {
        super(MARK_COMMAND);
        this.toMark = toMark;
        this.taskIndex = taskIndex;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException, MissingTaskException, SaveFileErrorException {
        try {
            taskIndex = parser.removeMarkPrefix(taskIndex);
            int index = Integer.parseInt(taskIndex) - 1;
            tasks.updateTask(toMark, index);
            ui.printMarkUpdate(tasks, toMark, index);
            saveTask(storage, tasks, ui);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException();
        }
    }
}
