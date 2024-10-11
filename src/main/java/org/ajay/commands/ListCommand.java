package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.data.task.Task;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = Task.LIST_COMMAND;
    public static final String MESSAGE_USAGE = """
            Clears address book permanently.
            Example: """ + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    /**
     * Executes the list command.
     *
     * @param tasks   TaskList containing all tasks.
     * @param ui      TextUi object to interact with the user.
     * @param storage Storage object to save the task list.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        TaskList.printAllTasksWithStreams(tasks.getTaskList());
    }
}
