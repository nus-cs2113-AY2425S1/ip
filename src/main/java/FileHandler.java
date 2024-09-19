import classes.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileHandler {
    private static String pathName = "../data/Tasks.txt";
    public static void createFile() {
        try {
            File tasks = new File(pathName);
            if (tasks.createNewFile()) {
                System.out.print(Skeleton.LINE_BREAK);
                System.out.println("Tasks File created! Located at: " + tasks.getAbsolutePath());
                System.out.print(Skeleton.LINE_BREAK);
            } else {
                System.out.print(Skeleton.LINE_BREAK);
                System.out.println("Tasks File already exists. Its at: " + tasks.getAbsolutePath());
                System.out.print(Skeleton.LINE_BREAK);
            }
        } catch (IOException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Oops! An error occurred");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
    public static void addTask(Task task) throws IOException{
        FileWriter writer = new FileWriter(pathName);
        
    }
}
