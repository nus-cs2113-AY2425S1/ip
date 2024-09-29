package commands;

public class ByeCommand extends Command {

    public static final String MESSAGE_SUCCESS = "\tBye. Hope to see you again soon!";

    @Override
    public void execute() {
        System.out.println(MESSAGE_SUCCESS);
    }

}
