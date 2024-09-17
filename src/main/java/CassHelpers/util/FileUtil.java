package CassHelpers.util;

import CassHelpers.types.Deadline;
import CassHelpers.types.Event;
import CassHelpers.types.Task;
import CassHelpers.types.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtil {
    private final File dir;
    private final File file;
    public FileUtil(String directoryPath,String fileName) {
        this.dir = new File(directoryPath);
        createDir();
        this.file = new File(dir, fileName);
        createFile();
    }

    public void createDir() {
        try {
            if(!dir.exists()){
                if(!dir.mkdirs()){
                    throw new IOException("Could not create directory");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createFile() {
         try {
             if(!file.exists()){
                    if(!file.createNewFile()){
                        throw new IOException("Could not create file");
                    }
             }
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }
    }

    public void appendTaskToFile(Task task) {
        try {

            FileWriter fileWriter = new FileWriter(file.getPath(), true);
            fileWriter.write(task.toWritableString()+"\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file. ");
        }
    }

    public void writeTasksToFile(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(file.getPath());
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
        try {
        Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                Task task = taskFormatter(scanner.nextLine());
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
        }
        return taskList;
    }

    private Task taskFormatter(String line) {
        String[] lineParser = line.split(",");
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
