package main.java;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Performs the delete command, removing the specific task from the task list, using the task index.
     * This is followed by saving to the storage and notifying the user through a display from the UI.
     *
     * @param tasks The task list that the command will work on.
     * @param ui The UI component of the chat bot.
     * @param storage The storage component of the chat bot.
     * @throws KenChatException If the task list is empty, or if the task number is invalid.
     */
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
