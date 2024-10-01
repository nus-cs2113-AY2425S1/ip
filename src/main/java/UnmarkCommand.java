public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PoirotException {
        if (index < 1 || index > tasks.getTaskCount()) {
            throw new PoirotException("Task number is out of bounds!");
        }
        tasks.markTask(index - 1, false);
        ui.showUnmarkTask(tasks.getTask(index - 1));
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
