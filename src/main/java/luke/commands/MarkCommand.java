package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.LukeException;

public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.mark(inputs);
        } catch (LukeException e) {
            ui.printReply(e.getMessage());
        }
    }
}
