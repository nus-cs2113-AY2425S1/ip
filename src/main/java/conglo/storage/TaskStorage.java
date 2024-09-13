package conglo.storage;

import conglo.task.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskStorage {

    private static final String FILE_PATH = "./data/conglo.txt";
    private static final String DIRECTORY_PATH = "./data";

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path directoryPath = Paths.get(DIRECTORY_PATH);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length < 3) {
                    System.out.println("Invalid task format: " + line);
                    continue;
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("✓");
                String description = parts[2];

                Task task = null;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    if (parts.length < 4) {
                        System.out.println("Invalid deadline format: " + line);
                        continue;
                    }
                    task = new Deadline(description, parts[3].substring(3));
                    break;
                case "E":
                    if (parts.length < 4) {
                        System.out.println("Invalid event format: " + line);
                        continue;
                    }
                    String[] details = parts[3].split(" to ");
                    details[0] = details[0].substring(5);
                    task = new Event(description, details[0], details[1]);
                    break;
                }
                if (task != null && isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
