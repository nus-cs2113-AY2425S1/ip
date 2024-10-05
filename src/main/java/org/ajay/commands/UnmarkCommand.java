package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.data.task.Task;
import org.ajay.parser.Parser;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = Task.UNMARK_COMMAND_STRING;
    public static final String MESSAGE_USAGE = "Unmarks a task as done.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Task has been unmarked as done";

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        tasks.markAsUndone(Parser.task);
        storage.saveTaskList(tasks.getTaskList());
    }
}
