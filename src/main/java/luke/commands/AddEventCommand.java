package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.LukeException;

public class AddEventCommand extends Command{

    public static final String COMMAND_WORD = "event";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.addEvent(inputs);
        } catch (LukeException e) {
            ui.printReply(e.getMessage());
        }

    }
}
