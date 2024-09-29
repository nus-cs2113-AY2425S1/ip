package storage;

import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/test.txt";

    /**
     * Saves list of task into .txt file.
     * If directory and file do not exist, they will be created.
     * All the tasks are written in a specific format based on 'toFileFormat' method called.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File f = new File(FILE_PATH);
            File directory = f.getParentFile();

            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileFormat());
                fw.append("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads list of tasks from a .txt file.
     * Converts each task into a Task object using `getFileFormat` method.
     * If the file does not exist, nothing will be loaded.
     *
     * @return A list of tasks loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        File file = new File(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Task.getFileFormat(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
        }

        return tasks;
    }
}
