package apsea.command;

import apsea.exception.ApseaException;
import apsea.storage.Storage;
import apsea.task.TaskList;
import apsea.task.Todo;
import apsea.ui.Ui;

/**
 * Represents a command to add a todo to the task list.
 * <code>fullCommand</code> represents the user's input.
 */
public class AddTodoCommand extends Command {
    private String fullCommand;
    private final int NAME_POSITION = 5;
    private final String TODO_FORMAT_ERROR = "\tSorry, please use the format:\n" + "\ttodo [task name]";

    public AddTodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     *  Adds new todo to task list.
     *
     * @param taskList List of tasks.
     * @param ui Ui for displaying messages.
     * @throws ApseaException if user input is not empty.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws ApseaException {
        if (fullCommand.length() <= NAME_POSITION) {
            throw new ApseaException(TODO_FORMAT_ERROR);
        }
        taskList.addTask(new Todo(fullCommand.substring(NAME_POSITION)) );

        storage.saveData(taskList);
        ui.printAddTask(taskList);
        ui.printTotalTaskCount(taskList);
    }
}
