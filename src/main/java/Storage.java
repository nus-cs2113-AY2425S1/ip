import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.*;

public class Storage {

    /**
     * Loading any saved data from previous runs when Crystal is booted.
     *
     */
    public static void loadTaskList() {
        try {
            File file = new File("./data/Crystal.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String[] words = scan.nextLine().split(" \\| ");
                addTaskToList(words);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("No data to load.");
        }
    }

    /**
     * Saving any data changed into the data file. Will create a directory and
     * a file if needed, and new data will be rewritten into the file.
     *
     */
    public static void saveTaskList(ArrayList<Task> list) {
        try {
            // create separate dir for saved data if !exist()
            File dir = new File("./data");
            if (!dir.exists()) {
                if (dir.mkdir()) {
                    System.out.println("Directory created successfully.");
                } else {
                    System.out.println("Directory creation failed.");
                }
            }

            // create data file if !exist()
            File file = new File(dir, "Crystal.txt");
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Data file created successfully.");
                } else {
                    System.out.println("Data file creation failed.");
                }
            }

            // rewriting data in the list to the file
            FileWriter fw = new FileWriter(file);
            for (Task task : list) {
                fw.write(task.toFormatFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            Ui.printHorizontalLine();
            System.out.print("Error: Unable to retrieve data.");
            Ui.printExceptionMessage(e);
        }
    }

    public static void updateTask(boolean isDoneUpdated, Task t) {
        t.setIsDone(isDoneUpdated);
        TaskList.tasks.add(t);
        TaskList.taskCount++;
    }

    /**
     * Called in loadTaskList(), where each line of task saved in the data
     * file is processed in this method
     */
    public static void addTaskToList(String[] words) {
        String command = words[0];
        boolean isDoneUpdated = Boolean.parseBoolean(words[1]);
        try {
            switch (command) {
            case "T":
                Task todo = new Todo(words[2]);
                updateTask(isDoneUpdated, todo);
                break;
            case "D":
                Task deadline = new Deadline(words[2], words[3]);
                updateTask(isDoneUpdated, deadline);
                break;
            case "E":
                Task event = new Event(words[2], words[3], words[4]);
                updateTask(isDoneUpdated, event);
                break;
            default:
                throw new IOException();
            }
        } catch (IOException e) {
            Ui.printHorizontalLine();
            System.out.print("File is Corrupted.");
            Ui.printExceptionMessage(e);
        }
    }

}
