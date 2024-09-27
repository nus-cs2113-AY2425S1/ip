package luke;

import java.util.ArrayList;

public abstract class Command {
    boolean isExit;

    public Command() {

    }

    public abstract void execute(TaskList taskList, Ui ui, String[] inputArr);
}
