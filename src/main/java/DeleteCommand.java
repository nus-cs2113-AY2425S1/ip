public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(String taskIndexStr) throws AirBorderException {
        // Parse the task index from the user's input
        try {
            this.taskIndex = Integer.parseInt(taskIndexStr) - 1;
        } catch (NumberFormatException e) {
            throw new AirBorderException("Please provide a valid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AirBorderException {
        // Delete the specified task from the task list
        Task removedTask = tasks.deleteTask(taskIndex);
        // Display a message to the user
        ui.showTaskDeleted(removedTask, tasks.size());
        // Save the updated task list to storage
        storage.save(tasks);
    }
}
