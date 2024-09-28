package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit(){
        return true;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){};
}
