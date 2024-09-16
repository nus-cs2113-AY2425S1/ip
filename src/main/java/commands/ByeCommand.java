package commands;

import exception.SaveFileErrorException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.BYE_COMMAND;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(BYE_COMMAND);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveFileErrorException {
        saveTaskList(storage, tasks, ui);
        ui.saySayonara();
        setExit(true);
    }
}
