package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
        this.setExit();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        storage.write(taskList);
        System.out.println("Bye! Time for MacDonalds!");
    }
}
