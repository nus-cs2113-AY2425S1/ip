package AlyBot;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        ui.showList(taskList);
    }
}
