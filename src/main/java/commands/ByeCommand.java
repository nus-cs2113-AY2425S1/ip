package commands;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;

public class ByeCommand extends Command {

    public static final String MESSAGE_SUCCESS = "\tBye. Hope to see you again soon!";
    boolean isExit;

    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public String execute() {
        return MESSAGE_SUCCESS;
    }

}
