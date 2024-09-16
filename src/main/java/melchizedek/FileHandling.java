package melchizedek;

import melchizedek.task.Task;
import melchizedek.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandling {

    public static final String FILE_DIRECTORY = "./data";
    public static final String FILE_PATH = "./data/Melchizedek.txt";
    public static final String COPY_FILE_PATH = "./data/temp.txt";

    public static void loadFile(TaskList taskList) {
        File dir = new File(FILE_DIRECTORY);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.err.println("Error. No directory could be created.");
            }
        }
        try {
            File file = new File(FILE_PATH);
            if (!file.createNewFile()) {
                loadDataFromFile(FILE_PATH, taskList);
            }
        } catch (IOException e) {
            System.err.println("Error. No file could be created or found.");
        }
    }

    public static void loadDataFromFile(String filePath, TaskList taskList) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String input = s.nextLine();
            String[] tokens = input.split(" \\| ");
            switch (tokens[0].toLowerCase()) {
            case "T":
                taskList.addTodo(Arrays.copyOfRange(tokens, 1, tokens.length));
                break;
            case "D":
                taskList.addDeadline(Arrays.copyOfRange(tokens, 1, tokens.length));
                break;
            case "E":
                taskList.addEvent(Arrays.copyOfRange(tokens, 1, tokens.length));
                break;
            default:
                System.err.println("Unable to process line.");
                break;
            }
        }
    }

    public static void createCopyOfFile(String filePath) {
        try {
            Files.copy(Paths.get(FILE_PATH), Paths.get(COPY_FILE_PATH));
        } catch (IOException e) {
            System.err.println("Error. No file could be created or found.");
        }
    }

    public static void writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(taskList.taskListToFile());
            fw.close();
        } catch (IOException e) {
            System.err.println("Error. Cannot write to file.");
        }
    }
}
