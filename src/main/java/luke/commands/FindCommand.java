package luke.commands;

import luke.TaskList;
import luke.Ui;

public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";

    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        taskList.findTask(inputs);
    }
}
