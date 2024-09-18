package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/test.txt";

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

    public Task[] loadTasks() {
        File file = new File(FILE_PATH);
        Task[] tasks = new Task[100];
        int taskNumber = 0;

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Task.getFileFormat(line);
                tasks[taskNumber] = task;
                taskNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
        }

        return tasks;
    }


}

