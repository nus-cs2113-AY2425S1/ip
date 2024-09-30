public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(String index) {
        this.taskIndex = Integer.parseInt(index) - 1; // Convert from user input (1-based index) to 0-based index.
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        try {
            Task taskToRemove = tasks.getTasks().get(taskIndex);
            tasks.deleteTask(taskIndex);
            ui.show("Deleted task: " + taskToRemove);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task does not exist.");
        }
    }
}