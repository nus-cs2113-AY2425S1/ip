package utils;
import java.io.File;
import java.io.FileWriter; // Add this import statement
import java.util.ArrayList; // Add this import statement

import Tasks.Task;

public class LogFile {
    public static final String LOG_FILE_NAME = "./data/Cubone.txt";

    public static File getLogFile() {
        File file = new File(LOG_FILE_NAME);
        // check if the file exists
        if (!file.exists()) {
            try {
                // check if the dictionary exists
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void updateLogFile(ArrayList<Task> inputed_tasks) {
        try {
            // run through the list of tasks and write them to the file
            File file = getLogFile();
            FileWriter fileWritter = new FileWriter(file);
            for (Task task : inputed_tasks) {
                fileWritter.write(task.toString() + "\n");
            }
            fileWritter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
