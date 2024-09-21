package niwa.command;

import niwa.Niwa;
import niwa.data.Storage;
import niwa.data.task.*;
import niwa.exception.NiwaDuplicateTaskException;

import java.io.BufferedReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;

public class ReadCommand extends Command{
    public ReadCommand() {
        setFormat("^(?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\.\\/)?([\\w.-]+[\\\\/])*[\\w.-]+\\.txt$");
        setWord("read");
        setGuide("read [.txt file path]: Read the tasks in the file and add to the list.");
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

        if (!arguments[0].equals(Niwa.getOutputFilePath())) {
            ExecutedCommand.saveTasks();
        }
    }
}
