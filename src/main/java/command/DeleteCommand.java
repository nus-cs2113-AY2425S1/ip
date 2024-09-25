package command;

import exception.MondayException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException {
        int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
        if (tasks.isValidTaskNumber(taskNumber)) {
            ui.showTaskRemoved(tasks.remove(taskNumber), tasks.size());
            storage.save(tasks.getTasks());
        } else {
            throw new MondayException("Invalid task number.");
        }
    }
}
