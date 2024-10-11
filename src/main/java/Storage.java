import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Storage class handles loading and saving tasks to and from a file.
 * It interacts with the FileHandler to perform file operations and Fenix to
 * load and store tasks.
 */
public class Storage {
    private Fenix fenix;
    private FileHandler fileHandler;

    /**
     * Constructs a Storage instance associated with the given Fenix instance.
     * Initializes the file handler to manage file operations.
     *
     * @param fenix The Fenix instance that this Storage interacts with.
     */
    public Storage(Fenix fenix) {
        this.fenix = fenix;
        fileHandler = new FileHandler();
    }

    /**
     * Loads all task information from the file.
     * If the file contains task data, it is parsed and loaded into Fenix.
     */
    public void loadAllInfo() {
        try {
            String fileContent = getFileInfo();
            if (!fileContent.isBlank()) {
                decipherAllInfo(fileContent);
            }
        } catch (FileNotFoundException ignored) {
        }
    }

    /**
     * Reads and returns the contents of the task file.
     *
     * @return A string representing the file contents.
     * @throws FileNotFoundException if the file cannot be found.
     */
    private String getFileInfo() throws FileNotFoundException {
        String fileContent = "";
        fileContent = fileHandler.loadFileContents();
        return fileContent;
    }

    /**
     * Parses the file content and loads each task into Fenix.
     * The file content is split into individual tasks, and each task is added to the task list.
     *
     * @param fileContent The file content containing task information.
     */
    private void decipherAllInfo(String fileContent) {
        String[] arrayOfTasks = fileContent.split("\n");
        for (String task : arrayOfTasks) {
            String[] stringArray = task.split("\\|");
            String taskType = stringArray[1];
            String taskStatus = stringArray[2];
            String taskInfo = stringArray[3].trim();
            fenix.addTaskFromStorage(taskType, taskStatus, taskInfo);
        }
    }

    /**
     * Saves all tasks to the file by first clearing the file, then writing all tasks.
     */
    public void writeAllTasks() {
        clearAllInfo();
        saveAllInfo();
    }

    /**
     * Clears all task information from the file by overwriting it with an empty string.
     */
    private void clearAllInfo() {
        try {
            fileHandler.writeToFile("");
        } catch (IOException | NullPointerException ignored) {
        }
    }

    /**
     * Saves all current tasks in Fenix to the file.
     * Each task is formatted and appended to the file.
     */
    private void saveAllInfo() {
        for (Task task : fenix.getTaskArrayList()) {
            try {
                String taskToWrite = task.toString();
                taskToWrite = taskToWrite.replaceAll("\\[", "|");
                taskToWrite = taskToWrite.replaceAll("]", "|");
                taskToWrite = taskToWrite.replace("||", "|");
                fileHandler.appendToFile(taskToWrite);
                fileHandler.appendToFile("\n");
            } catch (IOException | NullPointerException e) {
                return;
            }
        }
    }
}
