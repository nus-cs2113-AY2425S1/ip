package commands;

import exceptions.XiaoMeException;
import task.Task;

import java.util.ArrayList;

public class Command {
    protected ArrayList<Task> tasks;
    boolean isExit;

    public String execute(ArrayList<Task> tasks) throws XiaoMeException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

//    public static void setData(ArrayList<Task> tasks) {
//        this.tasks = tasks;
//    }

    public boolean isExit() {
        return isExit;
    }
}
