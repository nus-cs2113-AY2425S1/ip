package jeremy;

import jeremy.util.PrintUtils;
import jeremy.util.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final String PATH = "./src/main/java/jeremy/data/";
    private static final String FILE_NAME = "Jeremy.txt";

    public static void saveData(TaskList tasks) {
        File file = new File(PATH + FILE_NAME);

        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).toStorageString() + System.lineSeparator());
            }
        } catch (IOException e) {
            PrintUtils.println("Unable to save data: " + e.getMessage());
        }
    }
}
