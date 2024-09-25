package command;

import exception.MondayException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException {
        int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
        if (tasks.isValidTaskNumber(taskNumber)) {
            tasks.markAsDone(taskNumber);
            ui.showTaskMarkedDone(tasks.get(taskNumber));
            storage.save(tasks.getTasks());
        } else {
            throw new MondayException("Invalid task number.");
        }
    }
}
