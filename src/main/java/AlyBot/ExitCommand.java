package AlyBot;

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
