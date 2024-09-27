package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.IncorrectInput;
import luke.exceptions.InsufficientArguments;

public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputArr) {
        try {
            taskList.deleteTask(inputArr);
        } catch (InsufficientArguments | IncorrectInput e) {
            ui.printReply(e.getMessage());
        }
    }
}
