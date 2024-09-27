package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.InsufficientArguments;

public class AddTodoCommand extends Command{

    public static final String COMMAND_WORD = "todo";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputArr) {
        try {
            taskList.addToDo(inputArr);
        } catch (InsufficientArguments e) {
            ui.printReply(e.getMessage());
        }
    }
}
