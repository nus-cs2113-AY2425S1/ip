
import classes.Deadlines;
import classes.Event;
import classes.Task;
import classes.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {
    private static final String pathName = "../data/Tasks.txt";
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
    public static void addTask(String text) throws IOException {
            FileWriter writer = new FileWriter(pathName, true);
            writer.write(text);
            writer.write(System.lineSeparator());
            writer.close();
    }
    public static Task[] readTasks() throws IOException {
        File tasks = new File(pathName);
        Scanner input = new Scanner(tasks);
        Task[] list = new Task[100];
        int length = 0;
        while (input.hasNext()) {
            String[] splitInput = input.nextLine().split("\\| ");
            if (input.nextLine().contains("[T]")){
                list[length] = new Todo(splitInput[2].trim(), Boolean.getBoolean(splitInput[1].trim()));
            } else if (input.nextLine().contains("[D]")) {
                list[length] = new Deadlines(splitInput[2].trim(), Boolean.getBoolean(splitInput[1].trim()),
                        splitInput[3].trim());
            } else if (input.nextLine().contains("[E]")) {
                list[length] = new Event(splitInput[2].trim(), Boolean.getBoolean(splitInput[1].trim()),
                        splitInput[3].trim(), splitInput[4].trim());
            }
            length++;
        }
        return list;
    }
}
