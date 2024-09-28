package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage ){
        String message = String.format("Here are the tasks in your list:%n%s",tasks);
        ui.showMsg(message);
    }
}
