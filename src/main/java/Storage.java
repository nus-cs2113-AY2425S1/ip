import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the loading and saving of tasks to and from a txt file at a predefined path.
 * It provides methods to read tasks from a file and save tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the filePath.
     *
     * @return an ArrayList of Task objects loaded from the file
     * @throws FileNotFoundException if the file does not exist
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException{
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;

                switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "D":
                    task = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "E":
                    task = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case " ":
                    task = new Task(parts[2]);
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                }
            }
            sc.close();
        }

        return tasks;
    }

    /**
     * Saves the provided TaskList to the file specified in the filePath.
     *
     * @param tasks the TaskList containing tasks to be saved
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void saveTasks(TaskList tasks) throws IOException {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // Create the directory if it doesn't exist
        }

        FileWriter fw = new FileWriter(file);
        for (Task task : tasks.getAllTasks()) {
            fw.write(task.toSaveFormat() + "\n");
        }
        fw.close();
    }
}
