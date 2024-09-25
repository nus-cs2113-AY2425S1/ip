package AlyBot;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlyException {
        ui.help();
    }
}