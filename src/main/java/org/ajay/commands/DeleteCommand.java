package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.data.exceptions.IllegalArgumentException;
import org.ajay.data.task.Task;
import org.ajay.parser.Parser;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = Task.DELETE_COMMAND_STRING;
    public static final String MESSAGE_USAGE = """
                                               Delete a task from the list.
                                               Example: """ + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Task has been deleted";

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            tasks.deleteTask(Parser.task);
        } catch (IllegalArgumentException e) {
            ui.printExceptions(e.getMessage());
            return;
        }
        storage.saveTaskList(tasks.getTaskList());
    }

}
