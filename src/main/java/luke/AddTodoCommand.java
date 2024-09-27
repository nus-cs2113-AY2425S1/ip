package luke;

import luke.exceptions.InsufficientArguments;

import java.util.ArrayList;

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
