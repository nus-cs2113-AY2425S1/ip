package luke.commands;

import luke.TaskList;
import luke.Ui;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputs) {
        taskList.list();
    }
}
