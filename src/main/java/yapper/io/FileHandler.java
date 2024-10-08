package yapper.io;

import java.io.File;
import java.io.IOException;

import yapper.tasks.TaskHandler;

/**
 * Initializes save file and task handler for Yapper.
 */
public class FileHandler {
    private static TaskHandler taskHandler;

    /**
     * Constructs a FileHandler, initialising save file and its folders.
     */
    FileHandler() {
        taskHandler = new TaskHandler();
        File file = new File(StringStorage.SAVE_FILE_PATH);
        try {
            initSaveFile(file);
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }
    /**
     * Initializes the save file path and the save file, creating them if they don't exist yet.
     *
     * @throws IOException if an I/O error occurs while creating the new save file.
     */
    public void initSaveFile(File file) throws IOException {
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        System.out.println("Searching for a save file ... ");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("No save file has been found. Starting with an empty task list. ");
        } else {
            System.out.println("A save file has been found. Initialising the task list ... ");
        }
    }

    public static TaskHandler getTaskHandler() {
        return taskHandler;
    }
}
