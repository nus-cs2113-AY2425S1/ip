import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class handles the reading and writing of task data to and from a file.
 * It ensures that tasks are persisted between sessions by storing them in a text file.
 */
public class Storage {

    // Path to the folder where the data file is stored
    private static String folderPath = "./data";

    // Path to the file where the tasks are saved
    private static String filePath = folderPath + "/duke.txt";

    // Reference to the TaskList instance for managing tasks
    private static TaskList taskListInstance;

    /**
     * Constructs a Storage object with a reference to a TaskList instance.
     *
     * @param taskList The TaskList instance used to store and retrieve tasks.
     */
    public Storage(TaskList taskList) {
        this.taskListInstance = taskList;
    }

    /**
     * Loads task data from the file and populates the TaskList instance.
     * If the file or directory does not exist, they are created.
     *
     * @throws DukeException If there is an error in loading the file data.
     * @throws IOException If there is an issue accessing the file system.
     */
    public void loadFileData() throws DukeException, IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                loadLineData(line);
            }
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            boolean succeed = file.createNewFile();
        }
    }

    /**
     * Loads a single line of data from the file and adds the corresponding task to the TaskList.
     *
     * @param line A line of data from the file representing a task.
     * @throws DukeException If there is an error parsing the line data.
     */
    public static void loadLineData(String line) throws DukeException {
        String[] inputComponent = line.split(" \\| ");
        switch (inputComponent[0]) {
            case "T" -> {
                TaskList.add(new ToDo(inputComponent[1]));
                if (inputComponent[2].equals("1")) {
                    taskListInstance.getTask(TaskList.getTaskCount() - 1).markAsDone();
                }
            }
            case "D" -> {
                TaskList.add(new Deadline(inputComponent[1], inputComponent[2]));
                if (inputComponent[3].equals("1")) {
                    taskListInstance.getTask(TaskList.getTaskCount() - 1).markAsDone();
                }
            }
            case "E" -> {
                TaskList.add(new Event(inputComponent[1], inputComponent[2], inputComponent[3]));
                if (inputComponent[4].equals("1")) {
                    taskListInstance.getTask(TaskList.getTaskCount() - 1).markAsDone();
                }
            }
        }
    }

    /**
     * Creates a string representation of a task to be written to the file.
     * The string follows the format "T | description | status" for ToDo tasks,
     * and includes additional details for Deadline and Event tasks.
     *
     * @param task The task to be serialized to a string.
     * @return The string representation of the task.
     * @throws DukeException If there is an error processing the task data.
     * @throws IOException If there is an issue during serialization.
     */
    public static String addLineData(Task task) throws DukeException, IOException {
        String line = "";
        if (task instanceof ToDo) {
            line += "T | " + task.getDescription() + " | ";
            line += task.isDone ? "1" : "0";
        } else if (task instanceof Deadline) {
            line += "D | " + task.getDescription() + " | " + ((Deadline) task).getBy() + " | ";
            line += task.isDone ? "1" : "0";
        } else if (task instanceof Event) {
            line += "E | " + task.getDescription() + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo() + " | ";
            line += task.isDone ? "1" : "0";
        }
        return line;
    }

    /**
     * Updates the file with the latest task data after every command.
     * This method overwrites the existing file with the current TaskList data.
     *
     * @throws DukeException If there is an error in generating the task data.
     * @throws IOException If there is an issue writing to the file.
     */
    public void updateFileData() throws DukeException, IOException {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (int i = 0; i < TaskList.getTaskCount(); i++) {
                writer.write(addLineData(taskListInstance.getTask(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DukeException("Cannot write to file");
        }
    }
}
