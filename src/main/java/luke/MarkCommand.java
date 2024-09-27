package luke;

import luke.exceptions.LukeException;

import java.util.ArrayList;

public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputArr) {
        try {
            taskList.mark(inputArr);
        } catch (LukeException e) {
            ui.printReply(e.getMessage());
        }
    }
}
