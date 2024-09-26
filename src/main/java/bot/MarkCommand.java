package bot;

import task.TaskList;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(index).markAsDone();
            ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.getTask(index));
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Invalid task number.");
        }
    }
}