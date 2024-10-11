import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class handles file operations such as saving, loading, and clearing tasks
 * from a text file. It works with a file that stores the task list in the local system and
 * allows persistent storage across sessions.
 */
public class Storage {

    private static java.io.File f;

    private static String path;

    /**
     * Initializes the storage system. It checks if the save file exists, creates it if necessary,
     * and loads the file contents into the task list.
     *
     * @throws IOException if an I/O error occurs during file creation or loading.
     */
    public static void init() throws IOException {
        f = new java.io.File(".data/SaveFile.txt");
        path = f.getAbsolutePath();
        if (!f.exists()) {
            if (f.getParentFile().mkdirs() && f.createNewFile()) {
                Ui.printSaveFileCreated();
            }
        }
        loadFile();
    }

    /**
     * Clears the contents of the save file, removing all stored tasks.
     *
     * @throws IOException if an I/O error occurs during file writing.
     */
    public static void clear() throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write("");
        fw.close();
    }

    /**
     * Appends a specified text string to the save file, used to save tasks in a formatted way.
     *
     * @param textToAppend The text to append to the save file.
     * @throws IOException if an I/O error occurs during file writing.
     */
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Loads the content of the save file into memory, converting it back into task objects
     * and adding them to the task list.
     *
     * @throws IOException if an I/O error occurs during file reading.
     */
    public static void loadFile() throws IOException {
        Scanner s = new Scanner(f);
        StringBuilder fileContent = new StringBuilder();
        while (s.hasNext()) {
            fileContent.append(s.nextLine());
            fileContent.append("\n");
        }
        String fileString = fileContent.toString();
        if (!fileString.isBlank()) {
            decipherAllInfo(fileString);
        }
    }

    /**
     * Converts the contents of the save file into tasks and adds them to the task list.
     *
     * @param fileContent The full content of the save file as a string.
     */
    public static void decipherAllInfo(String fileContent) {
        String[] arrayOfTasks = fileContent.split("\n");
        for (String task : arrayOfTasks) {
            String[] stringArray = task.split("\\|");
            String taskType = stringArray[0];
            String taskStatus = stringArray[1];
            String taskDesc = stringArray[2].trim();
            TaskList.addTaskFromSave(taskType, taskStatus, taskDesc);
        }
        Ui.printLoadConfirm();
    }

    /**
     * Saves the current tasks from the task list to the save file in a formatted manner. Each task
     * is converted to a string that can later be parsed when loading.
     */
    public static void save() {
        for (int i = 0; i < TaskList.listCount; i += 1) {
            try {
                String taskString = TaskList.tasks[i].toString();
                String stringToWrite = getStringToWrite(taskString);
                appendToFile(stringToWrite);
                appendToFile("\n");
            } catch (IOException|NullPointerException e) {
                return; // Exception handling during save process.
            }
        }
    }

    /**
     * Formats a task string by converting task details into a savable format for the save file.
     * This method modifies the task string by replacing certain characters with file-friendly markers
     * and abbreviating certain task details.
     *
     * @param taskString The original string representation of the task.
     * @return A formatted string ready to be written to the save file.
     */
    private static String getStringToWrite(String taskString) {
        String stringToWrite = taskString.substring(3); // Strips first 3 characters
        stringToWrite = stringToWrite.replaceAll("\\[", "|");
        stringToWrite = stringToWrite.replaceAll("]", "|");
        stringToWrite = stringToWrite.replaceAll("BY:", "/by");
        stringToWrite = stringToWrite.replaceAll("FROM:", "/from");
        stringToWrite = stringToWrite.replaceAll("TO:", "/to");
        stringToWrite = stringToWrite.replace("||", "|");
        return stringToWrite;
    }
}
