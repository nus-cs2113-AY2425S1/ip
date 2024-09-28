package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.InsufficientArgumentsException;

public class AddTodoCommand extends Command{

    public static final String COMMAND_WORD = "todo";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.addToDo(inputs);
        } catch (InsufficientArgumentsException e) {
            ui.printReply(e.getMessage());
        }
    }
}
