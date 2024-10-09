package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import tasks.TaskList;
import ui.Skeleton;


/**
 * The TaskEncoder class handles encoding information
 *  onto the text file located at the {@code pathName}
 */
public class TaskEncoder {
    private static final String pathName = "./data/Tasks.txt";
    private static final Path path = Path.of("./data");

    /**
     * Checks if the text file and directory exists and
     * creates the directory and text file to be read and written on
     */
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

    /**
     * Overrides the text file located at pathName and
     * copies all the tasks on the given taskList onto the text file
     * @param taskList the current taskList
     * @throws IOException when write throws an IOException
     */
    public static void overrideFile(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(pathName);
        for (Task task : taskList.getTasks()) {
            writer.write(task.toFileFormat() + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Adds a task to the text file by appending to the text file
     * @param text the task formatted into a String
     * @throws IOException when there is an input output error
     */
    public static void addTask(String text) throws IOException {
        FileWriter writer = new FileWriter(pathName, true);
        writer.write(text);
        writer.write(System.lineSeparator());
        writer.close();
    }
    /**
     * Replaces the word false in the corresponding task in the text file with true
     * @param index of the task to be marked
     * @throws IOException when there is an input output error
     */
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

    /**
     * Replaces the word true in the corresponding task in the text file with false
     * @param index of the task to be marked
     * @throws IOException when there is an input output error
     */
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

    /**
     * Deletes the task given by the input index in the text file
     * @param index of the task to be deleted in the list
     * @throws IOException when there is an input output error
     */
    public static void deleteTask(int index) throws IOException {
        File tasks = new File(pathName);
        Scanner input = new Scanner(tasks);
        ArrayList<String> tempList = new ArrayList<>();
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
