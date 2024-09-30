package commands;

import task.Task;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import java.util.ArrayList;

public class ByeCommand extends Command {

    public static final String MESSAGE_SUCCESS = "\tBye. Hope to see you again soon!";

    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(ArrayList<Task> tasks) {
        return MESSAGE_SUCCESS;
    }

}
