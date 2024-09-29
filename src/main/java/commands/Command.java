package commands;

import exceptions.XiaoMeException;
import task.Task;

import java.util.ArrayList;

public class Command {
    protected ArrayList<Task> tasks;

    public String execute() throws XiaoMeException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    public void setData(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
