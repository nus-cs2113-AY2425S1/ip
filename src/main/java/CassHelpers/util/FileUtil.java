package CassHelpers.util;

import CassHelpers.types.Deadline;
import CassHelpers.types.Event;
import CassHelpers.types.Task;
import CassHelpers.types.Todo;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtil {
    private String fileName;

    public FileUtil(String fileName) {
        this.fileName = fileName;
    }

    public void appendTasktoFile(Task task) {
        try {
            File file = new File(this.fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(this.fileName, true);
            fileWriter.write(task.toWritableString()+"\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file. ");
        }
    }

    public void writeTasksToFile(ArrayList<Task> taskList) {
        try {
            File file = new File(this.fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(this.fileName);
            for (Task task : taskList) {
                fileWriter.write(task.toWritableString()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file. ");
        }
    }

    public ArrayList<Task> readTaskFromFile(){
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = taskFormatter(line);
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
        }
        return taskList;
    }

    private Task taskFormatter(String line) {
        String lineParser[] = line.split(",");
        Task task = new Task("dummy");
        switch (lineParser[0]){
            case "T":
                task = new Todo(lineParser[2]);
                task.setCompleted(lineParser[1].equals("1"));
                break;
            case "D":
                task = new Deadline(lineParser[2],lineParser[3]);
                task.setCompleted(lineParser[1].equals("1"));
                break;
            case "E":
                task=new Event(lineParser[2],lineParser[3],lineParser[4]);
                task.setCompleted(lineParser[1].equals("1"));
                break;
            default:
                System.out.println("An error occurred while parsing a task.");
        }
        return task;
    }
}
