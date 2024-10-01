package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.TaskList;
import apsea.task.Todo;
import apsea.ui.Ui;

public class AddTodoCommand extends Command {
    private String fullCommand;
    private final int NAME_POSITION = 5;
    private final String TODO_FORMAT_ERROR = "\tSorry, please use the format:\n" + "\ttodo [task name]";

    public AddTodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void runCommand(TaskList tasklist, Ui ui) throws ApseaException {
        if (fullCommand.length() <= NAME_POSITION) {
            throw new ApseaException(TODO_FORMAT_ERROR);
        }
        tasklist.addTask(new Todo(fullCommand.substring(NAME_POSITION)) );

        ui.printAddTask(tasklist);
        ui.printTotalTaskCount(tasklist);
    }
}
