
import classes.Deadlines;
import classes.Event;
import classes.Task;
import classes.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static void markTask(int index) throws IOException {
        File tasks = new File(pathName);
        Scanner input = new Scanner(tasks);
        ArrayList<String> tempList = new ArrayList<>();
        while (input.hasNextLine()) {
            tempList.add(input.nextLine());
        }
        tempList.set(index, tempList.get(index).replace("false", "true"));
        FileWriter writer = new FileWriter(pathName, false);
        for (String line : tempList) {
            System.out.println(line);
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }
    public static void unmarkTask(int index) throws IOException {
        File tasks = new File(pathName);
        Scanner input = new Scanner(tasks);
        ArrayList<String> tempList = new ArrayList<>();
        while (input.hasNextLine()) {
            tempList.add(input.nextLine());
        }
        tempList.set(index, tempList.get(index).replace("true", "false"));
        FileWriter writer = new FileWriter(pathName, false);
        for (String line : tempList) {
            System.out.println(line);
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }
    public static void deleteTask(int index) throws IOException{
        File tasks = new File(pathName);
        Scanner input = new Scanner(tasks);
        ArrayList<String> tempList = new ArrayList<>();
        String holder;
        int counter = 0;
        while (input.hasNextLine()) {
            holder = input.nextLine();
            if (counter != index) {
                tempList.add(holder);
            }
        }
        tempList.set(index, tempList.get(index).replace("true", "false"));
        FileWriter writer = new FileWriter(pathName, false);
        for (String line : tempList) {
            System.out.println(line);
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }
    public static Task[] readTasks() throws IOException {
        File tasks = new File(pathName);
        Scanner input = new Scanner(tasks);
        Task[] list = new Task[100];
        int length = 0;
        while (input.hasNextLine()) {
            String in = input.nextLine();
            String[] splitInput = in.split(" \\| ");
            if (splitInput[0].equals("[T]")) {
                list[length] = new Todo(splitInput[2], Boolean.parseBoolean(splitInput[1]));
            } else if (splitInput[0].equals("[D]")) {
                list[length] = new Deadlines(splitInput[2], Boolean.parseBoolean(splitInput[1]),
                        splitInput[3]);
                System.out.println(splitInput[0]);
            } else if (splitInput[0].equals("[E]")) {
                list[length] = new Event(splitInput[2], Boolean.parseBoolean(splitInput[1]),
                        splitInput[3], splitInput[4]);
            }
            length++;
        }
        return list;
    }
}
