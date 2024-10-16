package yapper.io;

import java.io.File;
import java.io.IOException;

import yapper.tasks.TaskHandler;

/**
 * Initializes save file and task handler for Yapper.
 */
public class FileHandler {
    /**
     * The {@code TaskHandler} that manages the task list in Yapper.
     * This task list can be accessed from any FileHandler class.
     */
    private static TaskHandler taskHandler;

    /**
     * Constructs a FileHandler, initialising save file and its folders.
     */
    public FileHandler() {
        taskHandler = new TaskHandler();
        initSaveFileAndFolder(true);
    }

    /**
     * Initializes the save file and the folder it is located in for Yapper.
     * <p>
     * Checks if the save folder and save file exist, and creates them if necessary.
     * It differentiates between the initial startup and runtime operations based on the
     * {@code hasJustStarted} flag.
     *
     * @param hasJustStarted A boolean flag indicating whether the application has just started.
     *                       If {@code true}, startup messages will be printed; otherwise, runtime messages.
     * @throws IOException If an I/O error occurs while accessing or creating the save file or folder.
     */
    public static void initSaveFileAndFolder(boolean hasJustStarted) {
        try {
            File file = new File(StringStorage.SAVE_FILE_PATH);
            initSaveFolder(file, hasJustStarted);
            initSaveFile(file, hasJustStarted);
            System.out.println(StringStorage.LINE_DIVIDER);
        } catch (IOException e) {
            System.out.println("IOException occurred, with message: " + e.getMessage());
        }
    }
    /**
     * Initializes the folder for the save file. If the folder does not exist, it will be created.
     * A message will be printed to the console to indicate the status of the folder creation.
     *
     * @param file the save file for which the parent folder is to be checked or created.
     * @param hasJustStarted a flag to indicate if this is the first time the program is starting up.
     */
    private static void initSaveFolder(File file, boolean hasJustStarted) {
        File parentDir = file.getParentFile();
        System.out.println(hasJustStarted
                ? StringStorage.STARTUP_FIND_SAVE_FOLDER
                : StringStorage.RUNTIME_FIND_SAVE_FOLDER);
        if (!parentDir.exists()) {
            parentDir.mkdirs();
            System.out.println(hasJustStarted
                    ? StringStorage.STARTUP_IF_SAVE_FOLDER_NOT_FOUND
                    : StringStorage.RUNTIME_IF_SAVE_FOLDER_NOT_FOUND);
        } else {
            System.out.println(hasJustStarted
                    ? StringStorage.STARTUP_IF_SAVE_FOLDER_FOUND
                    : StringStorage.RUNTIME_IF_SAVE_FOLDER_FOUND);
        }
    }
    /**
     * Initializes the save file. If the file does not exist, it will be created. A message will be printed
     * to the console to indicate the status of the save file creation.
     *
     * @param file the save file to check or create.
     * @param hasJustStarted a flag to indicate if this is the first time the program is starting up.
     * @throws IOException if an I/O error occurs while creating the save file.
     */
    private static void initSaveFile(File file, boolean hasJustStarted) throws IOException {
        System.out.println(hasJustStarted
                ? StringStorage.STARTUP_FIND_SAVE_FILE
                : StringStorage.RUNTIME_FIND_SAVE_FILE);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println(hasJustStarted
                    ? StringStorage.STARTUP_IF_SAVE_FILE_NOT_FOUND
                    : StringStorage.RUNTIME_IF_SAVE_FILE_NOT_FOUND);
        } else {
            System.out.println(hasJustStarted
                    ? StringStorage.STARTUP_IF_SAVE_FILE_FOUND
                    : StringStorage.RUNTIME_IF_SAVE_FILE_FOUND);
        }
    }


    public static TaskHandler getTaskHandler() {
        return taskHandler;
    }


    /**
     * Checks if the save file exists at the path defined by {@code StringStorage.SAVE_FILE_PATH}.
     *
     * @return {@code true} if the save file exists, {@code false} otherwise.
     */
    public static boolean saveFileExists() {
        File file = new File(StringStorage.SAVE_FILE_PATH);
        return file.exists();
    }
    /**
     * Checks if the save file's folder exists. The folder is determined by the parent directory
     * of the file located at {@code StringStorage.SAVE_FILE_PATH}.
     *
     * @return {@code true} if the folder exists, {@code false} otherwise.
     */
    public static boolean saveFolderExists() {
        File file = new File(StringStorage.SAVE_FILE_PATH);
        return file.getParentFile().exists();
    }
}
