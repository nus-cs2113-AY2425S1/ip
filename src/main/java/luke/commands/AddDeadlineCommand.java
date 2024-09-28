package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.InsufficientArguments;

public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.addDeadline(inputs);
        } catch (InsufficientArguments e) {
            ui.printReply(e.getMessage());
        }
    }
}
