package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "task/test.txt";

    public void saveTasks(Task[] tasks, int taskNumber) {
        try {
            File f = new File(FILE_PATH);
            File directory = f.getParentFile();

            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskNumber; i++) {
                fw.write(tasks[i].toFileFormat());
                fw.append("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }


}

