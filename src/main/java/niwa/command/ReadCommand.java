package niwa.command;

import niwa.Niwa;
import niwa.data.Storage;
import niwa.data.task.*;
import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaInvalidArgumentException;

import java.io.BufferedReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;

public class ReadCommand extends Command{
    public static final String COMMAND_WORD = "read";
    public static final String COMMAND_GUIDE = "read [.txt file path]: Read the tasks in the file and add to the list.";
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
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            tasks = storage.loadTaskList();
        } catch (IOException e) {
            System.out.println(PREFIX + e.getMessage());
        }

        for (Task task: tasks) {
            try {
                System.out.println(PREFIX + "Adding..." + task.getFileOutput());
                TaskList.getInstance().addTask(task);
            } catch (NiwaDuplicateTaskException e) {
                System.out.println(PREFIX + e.getMessage());
            }
        }

        if (!dataPath.equals(Niwa.getOutputFilePath())) {
            ExecutedCommand.saveTasks();
        }
    }
}
