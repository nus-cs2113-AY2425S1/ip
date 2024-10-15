package main.java;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
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
        Task removedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.save(tasks.getTasks());
        ui.showTaskRemoved(removedTask, tasks.getSize());
    }
}
