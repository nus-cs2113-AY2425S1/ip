package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import constants.Skeleton;



public class TaskEncoder {
    private static final String pathName = "./data/Tasks.txt";
    private static final Path path = Path.of("./data");
    public static void createFile() {
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
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
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }
    public static void deleteTask(int index) throws IOException {
        File tasks = new File(pathName);
        Scanner input = new Scanner(tasks);
        ArrayList<String> tempList = new ArrayList<>();
        String holder;
        while (input.hasNextLine()) {
            tempList.add(input.nextLine());
        }
        tempList.remove(index);
        FileWriter writer = new FileWriter(pathName, false);
        for (String line : tempList) {
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }
}
