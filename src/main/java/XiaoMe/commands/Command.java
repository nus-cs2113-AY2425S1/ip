package XiaoMe.commands;

import XiaoMe.TaskList;
import XiaoMe.XiaoMeException;

public class Command {
    boolean isExit;

    public String execute(TaskList tasks) throws XiaoMeException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    public boolean isExit() {
        return isExit;
    }
}
