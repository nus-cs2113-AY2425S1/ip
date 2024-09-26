package quinn.storage;

import quinn.exception.QuinnException;
import quinn.task.Deadline;
import quinn.task.Event;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.task.TaskType;
import quinn.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class manages the persistence of task data to and from a file.
 * It handles the initialization of the storage directory and file, as well as
 * reading from and writing to the storage file.
 */
public class Storage {
    /** The File object representing the data storage file. */
    private final File dataFile;

    /**
     * Constructs a new Storage object with the specified folder and file name.
     *
     * @param folderName the name of the folder to store the data file
     * @param fileName the name of the data file
     * @throws QuinnException if unable to initialize the directory
     * @throws IOException if unable to initialize the file
     */
    public Storage(String folderName, String fileName) throws QuinnException, IOException {
        File directory = initialiseDirectory(folderName);
        dataFile = initialiseFile(directory, fileName);
    }

    /**
     * Initializes the directory for data storage.
     *
     * @param folderName the name of the folder to be initialized
     * @return the File object representing the initialized directory
     * @throws QuinnException if unable to initialize the directory
     */
    private File initialiseDirectory(String folderName) throws QuinnException {
        File directory = new File(folderName);
        boolean hasDirectory = directory.exists();

        if (!hasDirectory) {
            hasDirectory = directory.mkdir();
        }

        if (hasDirectory) {
            return directory;
        } else {
            throw new QuinnException("\t" + "Unable to initialise directory");
        }
    }

    private File initialiseFile(File directory, String fileName) throws IOException {
        File file = new File(directory + "/" + fileName);
        boolean hasFile = file.exists();

        if (!hasFile) {
            hasFile = file.createNewFile();
        }

        if (hasFile) {
            return file;
        } else {
            throw new IOException("\t" + "Unable to initialise file");
        }
    }

    /**
     * Loads tasks from the data file and returns them as a TaskList.
     *
     * @return a TaskList containing all tasks loaded from the file
     * @throws QuinnException if an invalid task type is encountered
     * @throws IOException if an I/O error occurs while reading the file
     */
    public TaskList loadTasksFromFile() throws QuinnException, IOException {
        TaskList taskList = new TaskList();

        FileReader fileReader = new FileReader(dataFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] taskDetails = line.trim().split("\\|");
            String type = taskDetails[0].trim();
            boolean isDone = (Integer.parseInt(taskDetails[1].trim()) == 1);
            String description = taskDetails[2].trim();

            if (type.equals(TaskType.TODO.getAbbreviation())) {
                Task todoTask = new ToDo(description, isDone);
                taskList.addTask(todoTask);
            } else if (type.equals(TaskType.DEADLINE.getAbbreviation())) {
                String dueDateTime = taskDetails[3].trim();
                Task deadlineTask = new Deadline(description, dueDateTime, isDone);
                taskList.addTask(deadlineTask);
            } else if (type.equals(TaskType.EVENT.getAbbreviation())) {
                String startDateTime = taskDetails[3].trim();
                String endDateTime = taskDetails[4].trim();
                Task eventTask = new Event(description, startDateTime, endDateTime, isDone);
                taskList.addTask(eventTask);
            } else {
                // Error detection for any invalid type of tasks found in the
                // storage file. This should not happen since the user is only
                // allowed to create todo [T], deadline [D] and event [E] tasks.
                throw new QuinnException("INVALID TYPE OF TASK FOUND");
            }
        }

        bufferedReader.close();

        return taskList;
    }

    /**
     * Saves the given TaskList to the data file.
     *
     * @param taskList the TaskList to be saved to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void saveTasksToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            Task task = taskList.getTask(i);
            bufferedWriter.write(task.saveFormat() + System.lineSeparator());
        }

        bufferedWriter.close();
    }
}
