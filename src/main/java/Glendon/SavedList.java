package Glendon;

import Glendon.task.Deadline;
import Glendon.task.Event;
import Glendon.task.Task;
import Glendon.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Scanner;
public class SavedList {

    public static void createTaskFile(String filePath, ArrayList<Task> taskList) {
        File taskFile = new File(filePath);
        File parentFile = taskFile.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            new File("./data").mkdirs();
        }
        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            System.out.println("    Issue with creating file" + e.getMessage());
        }
    }

    public static void loadSavedTasks(String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file filePath
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String savedTask = s.nextLine();
            String[] content = savedTask.split("\\|");
            switch (content[0]) {
            case "T":
                taskList.add(new Todo(Integer.parseInt(content[1]), content[2]));
                break;
            case "D":
                taskList.add(new Deadline(Integer.parseInt(content[1]), content[2], content[3]));
                break;
            case "E":
                taskList.add(new Event(Integer.parseInt(content[1]), content[2], content[3], content[4]));
            }
        }
    }

    public static void saveTasks(String filePath, ArrayList<Task> taskList) {
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
            if (e instanceof FileNotFoundException) {
                System.out.println("    Error: File not found at " + filePath);
            } else if (e instanceof AccessDeniedException) {
                System.out.println("    Error: Access denied while saving to " + filePath);
            } else {
                System.out.println("    Unexpected error saving file: " + e.getMessage());
            }
            //System.out.println("    Issue with saving file");
        }
    }
}
