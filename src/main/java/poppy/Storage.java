package poppy;

import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static poppy.Parser.getString;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist: " + file.getAbsolutePath());
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Parser.processFile(input, taskList);
        }
        sc.close();
        return taskList;
    }

    public void save(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (Task task : taskList) {
            if (task != null) {
                String line = getString(task);
                if (task instanceof Deadline deadline) {
                    line += "| " + deadline.getBy();
                } else if (task instanceof Events event) {
                    line += "| " + event.getFrom();
                }
                fw.write(line + System.lineSeparator());
            }
        }
        fw.close();
    }

    public void createFileIfNotExists() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.createNewFile();
        }
    }
}

