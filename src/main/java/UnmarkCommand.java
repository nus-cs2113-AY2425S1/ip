package main.java;

/**
 * Represents a command to unmark a task as not completed.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Performs the unmark command indicating that the task is not yet completed.\
     *
     * @param tasks The task list that the command will work on.
     * @param ui The UI component of the chat bot.
     * @param storage The storage component of the chat bot.
     * @throws KenChatException If the task list is empty or the index is invalid.
     */
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
