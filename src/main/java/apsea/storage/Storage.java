package apsea.storage;

import apsea.task.Event;
import apsea.task.Deadline;
import apsea.task.Task;
import apsea.task.Todo;

import apsea.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private static final String FILE_DIR = "./data";
    private static final String FILE_PATH = "./data/Apsea.txt";
    private static final String MAKE_FILE_ERROR = "Sorry, there is an issue creating the data file.";
    private static final String SAVE_FILE_ERROR = "Sorry, there is an issue saving the data file.";
    private static final String SEPARATOR = "; ";

    public static void loadTodo(String [] words, TaskList taskList){
        boolean isDone = words[1].equals("1");
        String description = words[2];

        taskList.addTask(new Todo(description, isDone));
    }

    public static void loadDeadline(String [] words, TaskList taskList){
        boolean isDone = words[1].equals("1");
        String description = words[2];
        String by = words[3];

        taskList.addTask(new Deadline(description, isDone, by));
    }

    public static void loadEvent(String [] words, TaskList taskList){
        boolean isDone = words[1].equals("1");
        String description = words[2];
        String from = words[3];
        String to = words[4];

        taskList.addTask(new Event(description, isDone, from, to));
    }

    public static void loadData(TaskList taskList) throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] words = line.split(SEPARATOR);
            switch (words[0]) {
            case "T":
                loadTodo(words, taskList);
                break;
            case "D":
                loadDeadline(words, taskList);
                break;
            case "E":
                loadEvent(words, taskList);
                break;
            }
        }
    }

    public static void loadFile(TaskList taskList) {

        try {
            File dir = new File(FILE_DIR);
            File f = new File(FILE_PATH);

            //create data directory if it does not exist
            if (!dir.exists() && !dir.mkdirs()) {
                System.out.println(MAKE_FILE_ERROR);
            }

            //create file if it does not exist, else load data
            if (!f.createNewFile()) {
                loadData(taskList);
            }
        } catch (IOException e) {
            System.out.println(MAKE_FILE_ERROR);
        }
    }

    public static void saveData(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskList.getTotal(); i++) {
                fw.write((taskList.getTask(i)).toFile());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(SAVE_FILE_ERROR);
        }

    }
}
