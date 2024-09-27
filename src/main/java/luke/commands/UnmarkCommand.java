package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.LukeException;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputArr) {
        try {
            taskList.unmark(inputArr);
        } catch (LukeException e) {
            ui.printReply(e.getMessage());
        }
    }
}
