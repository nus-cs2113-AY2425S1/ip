public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMark;

    public MarkCommand(String taskIndexStr, boolean isMark) throws AirBorderException {
        // Parse the task index and determine if marking or unmarking
        try {
            this.taskIndex = Integer.parseInt(taskIndexStr) - 1;
            this.isMark = isMark;
        } catch (NumberFormatException e) {
            throw new AirBorderException("Please provide a valid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AirBorderException {
        Task task = tasks.getTask(taskIndex);
        if (isMark) {
            task.markAsDone();
            ui.showTaskDone(task);
        } else {
            task.markAsNotDone();
            ui.showTaskUndone(task);
        }
        // Save the updated task list to storage
        storage.save(tasks);
    }
}
