package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.IncorrectInputException;
import luke.exceptions.InsufficientArgumentsException;

public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.deleteTask(inputs);
        } catch (InsufficientArgumentsException | IncorrectInputException e) {
            ui.printReply(e.getMessage());
        }
    }
}
