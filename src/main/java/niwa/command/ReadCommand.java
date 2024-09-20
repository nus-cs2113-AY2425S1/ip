package niwa.command;

import niwa.Niwa;
import niwa.data.task.*;

import java.io.BufferedReader;
import java.nio.file.*;
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

        Path filePath = Paths.get(arguments[0]);
        Path directory = filePath.getParent();

        // Check if the directory exists
        if (directory != null && !Files.exists(directory)) {
            System.out.println(PREFIX+"Directory not found: " + directory);
            return;
        }

        // Check if the file exists
        if (!Files.exists(filePath)) {
            System.out.println(PREFIX+"File not found: " + filePath);
            return;
        }

        // Read the file and add to task list
        try (BufferedReader reader = Files.newBufferedReader(filePath)){
            String line;
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;

                Task temp = switch (line.charAt(0)) {
                    case 'T' -> ToDo.parseTask(line);
                    case 'D' -> Deadline.parseTask(line);
                    case 'E' -> Event.parseTask(line);
                    default -> null;
                };

                if(temp!=null) {
                    TaskList.getInstance().addTask(temp);
                    System.out.printf(PREFIX + "Adding %s... %s%n", temp.getType(), temp.getFullInfo());
                }
            }

        } catch (IOException e) {
            System.out.println(PREFIX+"Error occurred when reading the file: " + e.getMessage());
        }

        if (!arguments[0].equals(Niwa.getOutputFilePath())) {
            ExecutedCommand.saveTasks();
        }
    }
}
