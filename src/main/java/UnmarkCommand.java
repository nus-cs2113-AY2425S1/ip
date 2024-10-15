package main.java;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KenChatException {
        if (tasks.getSize() == 0) {
            throw new KenChatException(KenChatException.getTaskNotExistMessage());
        }
        if (index < 0 || index >= tasks.getSize()) {
            throw new KenChatException(KenChatException.getTaskNumberDoesNotExistMessage());
        }
        Task task = tasks.getTask(index);
        task.markAsUndone();
        storage.save(tasks.getTasks());
        ui.showTaskUnmarked(task);
    }
}
