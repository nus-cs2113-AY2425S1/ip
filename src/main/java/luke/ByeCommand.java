package luke;

import java.util.ArrayList;

public class ByeCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputArr) {
        this.isExit = true;
    }
}
