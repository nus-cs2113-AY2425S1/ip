package main.java;

/**
 * Represents a command to list all the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Performs the list command, displaying all the tasks in the task list via the UI.
     * If the task list is empty, an exception is thrown.
     *
     * @param tasks The task list that the command will work on.
     * @param ui The UI component of the chat bot.
     * @param storage The storage component of the chat bot.
     * @throws KenChatException If the task list is empty, which will display that there is not task in the tasklist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KenChatException {
        if (tasks.getSize() == 0) {
            throw new KenChatException(KenChatException.getTaskNotExistMessage());
        }
        ui.showTasksList(tasks.getTasks());
    }
}
