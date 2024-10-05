package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.task.Todo;
import org.ajay.parser.Parser;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = Todo.COMMAND_STRING;
    public static final String MESSAGE_USAGE = """
                                               Creates a todo task.
                                               Example: """ + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Todo has been created!";

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            tasks.addTask(new Todo(Parser.task));
            storage.saveTaskList(tasks.getTaskList());
        } catch (EmptyArgumentException e) {
            ui.printExceptions(e.getMessage());
        }

    }

}
