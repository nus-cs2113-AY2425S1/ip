package nova.command;

import nova.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    private static final String BYE_MESSAGE= "Bye. Hope to see you again soon!";

    public static boolean execute() {
        Ui.displayMessage(BYE_MESSAGE);
        return true;
    }
}
