package main.java;

/**
 * Performs a command to mark a task as completed in the task list.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Performs the mark command, marking the specific task as done.
     * Saves the updated task list to the storage and notifying the user through display via the UI
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
        task.markAsDone();
        storage.save(tasks.getTasks());
        ui.showTaskMarked(task);
    }
}
