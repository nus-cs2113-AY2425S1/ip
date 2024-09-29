package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.InsufficientArgumentsException;

public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.addDeadline(inputs);
        } catch (Exception e) {
            ui.printReply(e.getMessage());
        }
    }
}
