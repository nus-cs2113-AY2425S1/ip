package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.LukeException;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.unmark(inputs);
        } catch (LukeException e) {
            ui.printReply(e.getMessage());
        }
    }
}
