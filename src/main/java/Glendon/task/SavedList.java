package Glendon.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Glendon.Glendon;
public class SavedList {

    public static void createTaskFile(String filePath, Task[] taskList) {
        File taskFile = new File(filePath);
        File parentFile = taskFile.getParentFile();
        if (parentFile == null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            System.out.println("    Issue with creating file");
        }
    }

    public static void loadSavedTasks(String filePath, Task[] taskList) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file filePath
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String savedTask = s.nextLine();
            String[] content = savedTask.split("\\|");
            switch (content[0]) {
            case "T":
                taskList[Glendon.taskCounter++]= new Todo(Integer.parseInt(content[1]), content[2]);
                break;
            case "D":
                taskList[Glendon.taskCounter++] = new Deadline(Integer.parseInt(content[1]), content[2], content[3]);
                break;
            case "E":
                taskList[Glendon.taskCounter++] = new Event(Integer.parseInt(content[1]), content[2], content[3], content[4]);
            }
        }
    }

    public static void saveTasks(String filePath, Task[] taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList) {
                if (task == null) {
                    break;
                }
                fw.write(task.saveToFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("    Issue with saving file");
        }
    }
}
