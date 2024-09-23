package niwa.command;

import niwa.Niwa;
import niwa.data.Storage;
import niwa.data.task.TaskList;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Command {
    protected static final String PREFIX = "\t";
    public static final String COMMAND_WORD = "";
    public static final String COMMAND_GUIDE = "";
    public static final String[] COMMAND_KEYWORDS = {};

    protected Map<String, String> arguments = new HashMap<>();


    public abstract CommandResult execute() throws NiwaInvalidArgumentException;
    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    public static String autoSaveTasks() {
        Storage storage = new Storage(Niwa.getOutputFilePath());

        String message;
        try {
            storage.writeTaskList(TaskList.getInstance().getTaskList());
            message = String.format(NiwaMesssages.MESSAGE_SAVE_COMPLETE, Niwa.getOutputFilePath());
        } catch (IOException e) {
            message = String.format(NiwaMesssages.MESSAGE_SAVE_FAILED, e.getMessage());
        }
        return message;
    }
}
