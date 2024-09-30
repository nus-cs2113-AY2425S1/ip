public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(String taskIndex) throws BebeException {
        try {
            this.taskIndex = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new BebeException("Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        tasks.deleteTask(taskIndex);
        storage.save(tasks.getTasks());
    }
}
