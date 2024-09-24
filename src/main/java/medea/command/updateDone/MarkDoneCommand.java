package medea.command.updateDone;

public class MarkDoneCommand extends UpdateDoneCommand{
    public static final String COMMAND_WORD = "mark";

    public MarkDoneCommand(int index) {
        super(index, true);
    }
}
