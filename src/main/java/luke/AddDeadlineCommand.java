package luke;

import luke.exceptions.InsufficientArguments;

public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    @Override
    public void execute(TaskList taskList, Ui ui, String[] inputArr) {
        try {
            taskList.addDeadline(inputArr);
        } catch (InsufficientArguments e) {
            ui.printReply(e.getMessage());
        }
    }
}
