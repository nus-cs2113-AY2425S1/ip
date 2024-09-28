package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.InsufficientArguments;

public class AddTodoCommand extends Command{

    public static final String COMMAND_WORD = "todo";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.addToDo(inputs);
        } catch (InsufficientArguments e) {
            ui.printReply(e.getMessage());
        }
    }
}
