package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.data.task.Task;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = Task.LIST_COMMAND_STRING;
    public static final String MESSAGE_USAGE = """
            Clears address book permanently.
            Example: """ + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        TaskList.printAllTasks();
    }
}
