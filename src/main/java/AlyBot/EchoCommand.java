package AlyBot;

public class EchoCommand extends Command {

    public EchoCommand(String instructions) {
        super(instructions);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        try {
            System.out.println(instructions);
        } catch (Exception e) {
            throw new AlyException("I honestly don't know what happened... Try again bah", e);
        }
    }
}
