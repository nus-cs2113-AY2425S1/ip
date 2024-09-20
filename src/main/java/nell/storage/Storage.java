package nell.storage;

import nell.TaskList;

import java.io.File;
import java.io.IOException;

public class Storage {
    private File dataFile;
    private TaskList tasks;

    /**
     * Constructs a new Storage class using the file at the given file path
     *
     * @param filePath The file path of the file
     * @param tasks The list of tasks to which file data is loaded and saved
     */
    public Storage(String filePath, TaskList tasks) {
        try {
            this.dataFile = new File(filePath);
            this.tasks = tasks;
            File dataDirectory = this.dataFile.getParentFile();

            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }

            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Cannot find or create data file!");
        }
    }
}
