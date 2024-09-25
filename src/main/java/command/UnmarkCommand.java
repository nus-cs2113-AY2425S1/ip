package command;

import exception.MondayException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException {
        int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1; // Extracts task number from input
        if (tasks.isValidTaskNumber(taskNumber)) {
            tasks.markAsNotDone(taskNumber); // Method to mark the task as not done
            ui.showTaskUnmarked(tasks.get(taskNumber)); // Show unmarked task message
            storage.save(tasks.getTasks()); // Save the updated tasks
        } else {
            throw new MondayException("    Invalid task number.");
        }
    }
}
