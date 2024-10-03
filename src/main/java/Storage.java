import java.io.*;
import java.util.Scanner;
/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file to load/save tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an array.
     *
     * @return An array of loaded tasks.
     */
    public Task[] loadTasksFromFile() {
        Task[] tasks = new Task[100];
        int lastIndex = 0;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (taskType) {
                    case "T":
                        tasks[lastIndex] = new Todo(description);
                        break;
                    case "D":
                        String by = parts[3];
                        tasks[lastIndex] = new Deadline(description, by);
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        tasks[lastIndex] = new Event(description, from, to);
                        break;
                }

                tasks[lastIndex].setDone(isDone);
                lastIndex++;
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }
    /**
     * Saves the specified tasks to the file.
     *
     * @param tasks     The tasks to save.
     * @param lastIndex The number of tasks to save.
     */
    public void saveTasksToFile(Task[] tasks, int lastIndex) {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < lastIndex; i++) {
                writer.write(tasks[i].toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
