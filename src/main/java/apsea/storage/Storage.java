package apsea.storage;

import apsea.task.Event;
import apsea.task.Deadline;
import apsea.task.Todo;

import apsea.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {
    private static final String FILE_DIR = "./data";
    private static final String FILE_PATH = "./data/Apsea.txt";
    private static final String MAKE_FILE_ERROR = "Sorry, there is an issue creating the data file.";
    private static final String SAVE_FILE_ERROR = "Sorry, there is an issue saving the data file.";
    private static final String SEPARATOR = "; ";

    public Storage() {
    }
    /**
     * Writes an todo to the task list.
     * The todo created should have a description and completion status
     *
     * @param words Array of tokens of a saved todo.
     * @param taskList List of tasks.
     */
    public void loadTodo(String [] words, TaskList taskList){
        boolean isDone = words[1].equals("1");
        String description = words[2];

        taskList.addTask(new Todo(description, isDone));
    }

    /**
     * Writes a deadline to the task list.
     * The deadline created should have a description, completion status and a by description.
     *
     * @param words Array of tokens of a saved deadline.
     * @param taskList List of tasks.
     */
    public void loadDeadline(String [] words, TaskList taskList){
        boolean isDone = words[1].equals("1");
        String description = words[2];
        String by = words[3];

        taskList.addTask(new Deadline(description, isDone, by));
    }

    /**
     * Writes an event to the task list.
     * The event created should have a description, completion status, from and to descriptions.
     *
     * @param words Array of tokens of a saved event.
     * @param taskList List of tasks.
     */
    public void loadEvent(String [] words, TaskList taskList){
        boolean isDone = words[1].equals("1");
        String description = words[2];
        String from = words[3];
        String to = words[4];

        taskList.addTask(new Event(description, isDone, from, to));
    }

    /**
     * Executes methods to load saved tasks based on type of task in the data file.
     *
     * @param taskList List of tasks.
     * @throws FileNotFoundException if data file does not exist.
     */
    public void loadData(TaskList taskList) throws FileNotFoundException {
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

    /**
     * Creates a data file if it does not exist in the hard drive.
     * Otherwise, loads data file from the hard drive to be written into the task list.
     *
     * @param taskList List of tasks.
     */
    public void loadFile(TaskList taskList) {
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

    /**
     * Saves the data in the list of tasks into the hard drive.
     *
     * @param taskList List of tasks.
     */
    public void saveData(TaskList taskList) {
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
