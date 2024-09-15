import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SaveTaskList {
    static Path directoryPath = Path.of("./data");
    static Path filePath = directoryPath.resolve("data.txt");

    public static void saveTasks(ArrayList<Task> taskList) {
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
                System.out.println("Directory created: " + directoryPath);
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("File created: " + filePath);
            }

            try (var writer = new FileWriter(filePath.toFile())) {
                for (var item : taskList) {
                    writer.write(item + System.lineSeparator());
                }
                System.out.println("Task list data saved to file: " + filePath);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static List<String> loadTasks() {
        List<String> taskList;

        if (!Files.exists(directoryPath)) {
            System.out.println("Data directory does not exist");
        }

        try {
            if (Files.exists(filePath)) {
                taskList = Files.readAllLines(filePath);
                return taskList;
            } else {
                System.out.println("Data file does not exist: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return null;
    }
}



