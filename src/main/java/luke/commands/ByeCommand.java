package luke.commands;

import luke.TaskList;
import luke.Ui;

public class ByeCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        this.isExit = true;
    }
}
