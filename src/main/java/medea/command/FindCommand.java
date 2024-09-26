package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

public class FindCommand extends Command {
    private String filter;
    public static final String COMMAND_WORD = "find";


    public FindCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        String message = String.format("Here are the matching tasks:%n%s",tasks.toFilteredString(filter));
        ui.showMsg(message);
    }
}
