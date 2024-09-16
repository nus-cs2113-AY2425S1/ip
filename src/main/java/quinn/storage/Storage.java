package quinn.storage;

import quinn.exception.QuinnException;
import quinn.task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles create, read and write file operations.
 */
public class Storage {
    private final File dataFile;

    /**
     * Constructor to initialize an instance of Storage class with folder
     * name and file name.
     *
     * @param folderName Folder name of the quinn.storage file
     * @param fileName Name of the quinn.storage file
     * @throws QuinnException If the directory cannot be initialised
     * @throws IOException If the quinn.storage file cannot be initialised
     */
    public Storage(String folderName, String fileName) throws QuinnException, IOException {
        File directory = initialiseDirectory(folderName);
        dataFile = initialiseFile(directory, fileName);
    }

    /**
     * Initializes the directory of the quinn.storage file.
     * Creates the directory if it does not exist.
     *
     * @param folderName Folder name of the quinn.storage file
     * @return The file that represents the relative path of the directory
     * @throws QuinnException If the directory cannot be initialised
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

    /**
     * Initializes the quinn.storage file.
     * Creates the quinn.storage file if it does not exist.
     *
     * @param directory Relative path of the directory
     * @param fileName Name of the quinn.storage file
     * @return The quinn.storage file
     * @throws IOException If the quinn.storage file cannot be initialised
     */
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
     * Loads all the tasks in the quinn.storage file to the task list.
     *
     * @return The list containing the tasks
     * @throws IOException If the quinn.storage file or task cannot be read
     * @throws QuinnException If an invalid type of task is found
     */
    public List<Task> loadTasksFromFile() throws QuinnException, IOException {
        List<Task> tasks = new ArrayList<>();

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
                tasks.add(todoTask);
            } else if (type.equals(TaskType.DEADLINE.getAbbreviation())) {
                String byDateTime = taskDetails[3].trim();
                Task deadlineTask = new Deadline(description, byDateTime, isDone);
                tasks.add(deadlineTask);
            } else if (type.equals(TaskType.EVENT.getAbbreviation())) {
                String fromDateTimeInput = taskDetails[3].trim();
                String toDateTimeInput = taskDetails[4].trim();
                Task eventTask = new Event(description, fromDateTimeInput, toDateTimeInput, isDone);
                tasks.add(eventTask);
            } else {
                // Error detection for any invalid type of tasks found in the
                // quinn.storage file. This should not happen since the user is only
                // allowed to create todo [T], deadline [D] and event [E] tasks.
                throw new QuinnException("INVALID TYPE OF TASK FOUND");
            }
        }

        bufferedReader.close();

        return tasks;
    }

    /**
     * Saves all the tasks in the task list to the quinn.storage file.
     *
     * @param tasks List containing the tasks to be saved
     * @throws IOException If the tasks cannot be saved
     */
    public void saveTasksToFile(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            bufferedWriter.write(task.saveFormat() + System.lineSeparator());
        }

        bufferedWriter.close();
    }
}
