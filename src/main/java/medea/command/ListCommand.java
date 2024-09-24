package medea.command;

import medea.Storage;
import medea.TaskList;
import medea.Ui;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage ){
        String taskString = tasks.toString();
        ui.showMsg(taskString);
    }
}
