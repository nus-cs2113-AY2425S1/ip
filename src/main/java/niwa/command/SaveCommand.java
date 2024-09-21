package niwa.command;

import niwa.data.Storage;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;

public class SaveCommand extends Command{
    public SaveCommand() {
        setFormat("^(?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\.\\/)?([\\w.-]+[\\\\/])*[\\w.-]+\\.txt$");
        setWord("save");
        setGuide("save [.txt file path]: Save the task list to the given path.");
    }

    @Override
    public String[] parseArguments(String command) {
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(argumentFormat);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(command);

        // Check if the command string matches the expected pattern
        if (matcher.matches()) {
            // Return the segments as an array
            return new String[]{command};
        } else {
            // Return null if the command does not match the expected format
            return null;
        }
    }

    @Override
    public void execute(String path) {
        super.execute(path);

        String dataPath = arguments[0];
        Storage storage = new Storage(dataPath);

        try {
            String message = storage.writeTaskList(TaskList.getInstance().getTaskList());
            System.out.println(PREFIX+message);
        } catch (IOException e) {
            System.out.println(PREFIX+e.getMessage());
        }

    }
}
