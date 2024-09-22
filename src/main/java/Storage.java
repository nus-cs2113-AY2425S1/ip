import sleepy.Task.Deadline;
import sleepy.Task.Event;
import sleepy.Task.Task;
import sleepy.Task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_PATH = "sleepy.txt";

    //creates file if is empty
    public Storage() {
        String directoryPath = Paths.get("").toAbsolutePath() + File.separator + DIRECTORY_NAME;
        String filePath = directoryPath + File.separator + FILE_PATH;

        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error initializing storage: " + e.getMessage());
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskInfo = line.split("\\|");
                Task task;

                switch (taskInfo[0]) {
                    case "T":
                        task = new Todo(taskInfo[2]);
                        break;
                    case "D":
                        task = new Deadline(taskInfo[2], taskInfo[3]);
                        break;
                    case "E":
                        task = new Event(taskInfo[2], taskInfo[3], taskInfo[4]);
                        break;
                    default:
                        throw new IOException("Corrupted task format.");
                }

                if (taskInfo[1].equals("1")) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
