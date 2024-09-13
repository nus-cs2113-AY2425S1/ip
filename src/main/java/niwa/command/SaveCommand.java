package niwa.command;

import niwa.task.Task;

import java.io.BufferedWriter;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;

public class SaveCommand extends TaskCommand{
    public SaveCommand(List<Task> tasks) {
        super(tasks);
        setFormat("^(?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\.\\/)?([\\w.-]+[\\\\/])*[\\w.-]+\\.txt$");
        setWord("save");
        setGuide("save [.txt file path]: Save the task list to the given path.");
    }

    @Override
    public String[] convertArguments(String command) {
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

        Path filePath = Paths.get(arguments[0]);
        Path directory = filePath.getParent();

        // Check if the directory exists
        if (directory != null && !Files.exists(directory)) {
            try {
                // If the directory doesn't exist, create it
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println(PREFIX+"Failed to create necessary directory: " + e.getMessage());
                return;
            }
        }
        try {
            // Create or clear the file
            Files.write(filePath, new byte[0], StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println(PREFIX+"Failed to create necessary file: " + e.getMessage());
            return;
        }

        for (Task task: tasks) {
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)){
                writer.write(task.getFileOutput());
                writer.newLine();
            } catch (IOException e) {
                System.out.println(PREFIX+"Failed to save to the file: " + e.getMessage());
                return;
            }
        }

        System.out.println(PREFIX+"Save completed!");
    }
}
