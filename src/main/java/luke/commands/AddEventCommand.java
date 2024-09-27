package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.InsufficientArguments;

public class AddEventCommand extends Command{

    public static final String COMMAND_WORD = "event";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputArr) {
        try {
            taskList.addEvent(inputArr);
        } catch (InsufficientArguments e) {
            ui.printReply(e.getMessage());
        }

    }
}
