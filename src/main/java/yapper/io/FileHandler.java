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
        File file = new File(StringStorage.SAVE_FILE_PATH);
        try {
            initSaveFolder(file, true);
            initSaveFile(file, true);
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }

    /**
     * Initializes the folder for the save file. If the folder does not exist, it will be created.
     * A message will be printed to the console to indicate the status of the folder creation.
     *
     * @param file the save file for which the parent folder is to be checked or created.
     * @param hasJustStarted a flag to indicate if this is the first time the program is starting up.
     */
    public static void initSaveFolder(File file, boolean hasJustStarted) {
        File parentDir = file.getParentFile();
        System.out.println(hasJustStarted
                ? "Searching for the save file folder ..."
                : "Confirming the state of save file folder ...");
        if (!parentDir.exists()) {
            parentDir.mkdirs();
            System.out.println(hasJustStarted
                    ? "No folder has been found. Creating folder now ..."
                    : "Folder is missing. Creating folder now ...");
        } else {
            System.out.println(hasJustStarted
                    ? "Folder has been found. "
                    : "Folder is still there. ");
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
    public static void initSaveFile(File file, boolean hasJustStarted) throws IOException {
        System.out.println(hasJustStarted
                ? "Searching for the save file ... "
                : "Confirming the state of save file ...");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println(hasJustStarted
                    ? "No save file has been found. Starting with an empty task list. "
                    : "Save file is missing. Creating save file now ... ");
        } else {
            System.out.println(hasJustStarted
                    ? "A save file has been found. Initialising the task list ... "
                    : "Save file is still there. ");
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
