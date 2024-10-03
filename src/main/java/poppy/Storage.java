package poppy;

import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static poppy.Parser.getString;

/**
 * The {@code Storage} class handles file operations such as loading and saving
 * tasks to and from a file. It ensures that task data persists across different sessions.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for the {@code Storage} class.
     *
     * @param filePath The path of the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a file and returns them as an {@code ArrayList} of {@code Task} objects.
     *
     * @return A list of tasks loaded from the file.
     * @throws FileNotFoundException if the file does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist: " + file.getAbsolutePath());
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Parser.processFile(input, taskList);
        }
        sc.close();
        return taskList;
    }

    /**
     * Saves the current list of tasks to a file. Each task is converted into a string
     * before being written to the file.
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException if an I/O error occurs during file writing.
     */
    public void save(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (Task task : taskList) {
            if (task != null) {
                String line = getString(task);
                if (task instanceof Deadline deadline) {
                    line += "| " + deadline.getBy();
                } else if (task instanceof Events event) {
                    line += "| " + event.getFrom();
                }
                fw.write(line + System.lineSeparator());
            }
        }
        fw.close();
    }

    /**
     * Creates the file specified by {@code filePath} if it does not already exist.
     * If the directories in the file path do not exist, they are created.
     *
     * @throws IOException if an error occurs while creating the file or directories.
     */
    public void createFileIfNotExists() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.createNewFile();
        }
    }
}

