public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PoirotException {
        if (index < 1 || index > tasks.getTaskCount()) {
            throw new PoirotException("Task number is out of bounds!");
        }
        Task taskToDelete = tasks.getTask(index - 1);
        tasks.delete(index - 1);
        ui.showDeletedTask(taskToDelete, tasks.getTaskCount());
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
