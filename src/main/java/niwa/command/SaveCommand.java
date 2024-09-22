package niwa.command;

import niwa.data.Storage;
import niwa.data.task.Task;
import niwa.data.task.TaskList;
import niwa.exception.NiwaInvalidArgumentException;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;

public class SaveCommand extends Command{
    public static final String COMMAND_WORD = "save";
    public static final String COMMAND_GUIDE = "save [.txt file path]: Save the task list to the given path.";
    public static final String[] COMMAND_KEYWORDS = {""};

    public static final String PATH_FORMAT = "^(?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\.\\/)?([\\w.-]+[\\\\/])*[\\w.-]+\\.txt$";

    public boolean isCorrectPath(String path) {
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(PATH_FORMAT);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(path);

        return matcher.matches();
    }

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
    public void execute() throws NiwaInvalidArgumentException{
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }
        String dataPath = arguments.get(COMMAND_KEYWORDS[0]);

        if (!isCorrectPath(dataPath)) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE);
        }
        Storage storage = new Storage(dataPath);

        try {
            String message = storage.writeTaskList(TaskList.getInstance().getTaskList());
            System.out.println(PREFIX+message);
        } catch (IOException e) {
            System.out.println(PREFIX+e.getMessage());
        }

    }
}
