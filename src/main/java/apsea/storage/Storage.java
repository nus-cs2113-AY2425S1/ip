package apsea.storage;

import apsea.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static apsea.Apsea.loadTodo;

public class Storage {
    private static final String FILE_DIR = "./data";
    private static final String FILE_PATH = "./data/Apsea.txt";
    private static final String MAKE_FILE_ERROR = "Sorry, there is an issue creating the data file.";
    private static final String SAVE_FILE_ERROR = "Sorry, there is an issue saving the data file.";
    private static final String SEPARATOR = "; ";

    public static void loadData() throws FileNotFoundException {
        System.out.println("Loading data...");
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            System.out.println(line);
            String[] words = line.split("; ");
            System.out.println(words[0]);
            switch (words[0]) {
            case "T":
                loadTodo(words);
                break;
            case "D":
                //loadDeadline();
                break;
            case "E":
                //loadEvent();
                break;
            }
        }
    }

    public static void loadFile() {

        try {
            File dir = new File(FILE_DIR);
            File f = new File(FILE_PATH);

            //create data directory if it does not exist
            if (!dir.exists() && !dir.mkdirs()) {
                System.out.println(MAKE_FILE_ERROR);
            }

            //create file if it does not exist, else load data
            if (!f.createNewFile()) {
                loadData();
            }
        } catch (IOException e) {
            System.out.println(MAKE_FILE_ERROR);
        }
    }

    public static void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                System.out.println(task.toFile());
                fw.write(task.toFile());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(SAVE_FILE_ERROR);
        }

    }
}
