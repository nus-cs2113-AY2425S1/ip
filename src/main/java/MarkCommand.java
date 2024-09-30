public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean isDone;

    public MarkCommand(String taskIndex, boolean isDone) throws BebeException {
        try {
            this.taskIndex = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new BebeException("Invalid task number.");
        }
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        tasks.markTask(taskIndex, isDone);
        storage.save(tasks.getTasks());
    }
}
