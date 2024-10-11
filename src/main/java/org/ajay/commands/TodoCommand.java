package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.task.Todo;
import org.ajay.parser.Parser;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

/**
 * Represents a command to create a todo task.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = Todo.COMMAND_WORD;
    public static final String MESSAGE_USAGE = """
            Creates a todo task.
            Example: """ + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";

    /**
     * Prints the success message after adding the task.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage object.
     */
    private void printSuccessMessage(TaskList tasks, TextUi ui, Storage storage) {
        ui.printBreakLine();
        ui.printSuccess(MESSAGE_SUCCESS);
        ui.printSuccess(tasks.getLatestTask().toString());
        TaskList.printNumberOfTasks();
        ui.printBreakLine();

        storage.saveTaskList(tasks.getTaskList());
    }

    /**
     * Executes the todo command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage object.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            tasks.addTask(new Todo(Parser.task));
            storage.saveTaskList(tasks.getTaskList());
        } catch (EmptyArgumentException e) {
            ui.printExceptions(e.getMessage());
        }
        printSuccessMessage(tasks, ui, storage);
    }
}
