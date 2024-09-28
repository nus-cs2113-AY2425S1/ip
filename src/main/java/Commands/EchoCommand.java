package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

public class EchoCommand extends Command {

    public EchoCommand(String instruction) {
        super(instruction);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        try {
            System.out.println(instruction);
        } catch (Exception e) {
            throw new AlyException("I honestly don't know what happened... Try again bah", e);
        }
    }
}
