package niwa.command;

import niwa.data.Storage;
import niwa.data.task.Task;
import niwa.data.task.TaskList;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;
import niwa.utils.NiwaUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;

public class SaveCommand extends Command{
    public static final String COMMAND_WORD = "save";
    public static final String COMMAND_GUIDE = "save [.txt file path]: Save the task list to the given path.";
    public static final String[] COMMAND_KEYWORDS = {""};

    public static final String PATH_FORMAT = "^(?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\.\\/)?([\\w.-]+[\\\\/])*[\\w.-]+\\.txt$";

    public boolean isValidArguments() {
        if (arguments.size() != COMMAND_KEYWORDS.length) {
            return false;
        }
        for (String keyword: COMMAND_KEYWORDS) {
            if (!arguments.containsKey(keyword)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }
        String dataPath = arguments.get(COMMAND_KEYWORDS[0]);

        if (!NiwaUtils.isMatch(dataPath, PATH_FORMAT)) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }

        Storage storage = new Storage(dataPath);

        ArrayList<String> messages = new ArrayList<>();
        try {
            storage.writeTaskList(TaskList.getInstance().getTaskList());
            messages.add(String.format(NiwaMesssages.MESSAGE_SAVE_COMPLETE, dataPath));
        } catch (IOException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_SAVE_FAILED, e.getMessage()));
        }
        return new CommandResult(messages);
    }
}
