package melchizedek;

import melchizedek.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

    public static final String FILE_DIRECTORY = "./data";
    public static final String FILE_PATH = "./data/Melchizedek.txt";

    public static void loadFile(TaskList taskList) {
        File dir = new File(FILE_DIRECTORY);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Ui.printDirError();
            }
        }
        try {
            File file = new File(FILE_PATH);
            if (!file.createNewFile()) {
                loadDataFromFile(FILE_PATH, taskList);
            }
        } catch (IOException e) {
            Ui.printFileError();
        }
    }

    public static void loadDataFromFile(String filePath, TaskList taskList) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String input = s.nextLine();
            String[] tokens = input.split(" \\| ");
            switch (tokens[0].toUpperCase()) {
            case "T":
                taskList.loadTodo(Arrays.copyOfRange(tokens, 1, tokens.length));
                break;
            case "D":
                taskList.loadDeadline(Arrays.copyOfRange(tokens, 1, tokens.length));
                break;
            case "E":
                taskList.loadEvent(Arrays.copyOfRange(tokens, 1, tokens.length));
                break;
            default:
                Ui.printTaskError();
                break;
            }
        }
    }

    public static void writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(taskList.taskListToFile());
            fw.close();
        } catch (IOException e) {
            Ui.printWriteError();
        }
    }
}
