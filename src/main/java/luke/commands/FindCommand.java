package luke.commands;

import luke.TaskList;
import luke.Ui;
import luke.exceptions.LukeException;

public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        try {
            taskList.findTask(inputs);
        } catch (LukeException e) {
            ui.printReply(e.getMessage());
        }
    }
}
