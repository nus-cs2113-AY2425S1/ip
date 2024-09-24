package medea.command.updateDone;

public class UnmarkDoneCommand extends UpdateDoneCommand{
    public static final String COMMAND_WORD = "unmark";

    public UnmarkDoneCommand(int index) {
        super(index, false);
    }

}
