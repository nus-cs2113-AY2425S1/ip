import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * load and save from filePath and store taskList
 */
public class Storage {
    private static String filePath;
    private static int count = 0;
    private static TaskList taskList;

    /**
     * constructor of storage
     *
     * @param filePath path to load and store data
     * @param ui user interface
     */
    public Storage(String filePath, Ui ui) {
        Storage.filePath = filePath;
        taskList = new TaskList(ui);
    }

    /**
     * constructor of storage
     *
     * @throws AnkeException when no existing input loading file found
     */
    public static void load() throws AnkeException {
        System.out.println("Loading data from file");
        Scanner s;
        try {
            File f = new File(filePath);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new AnkeException(e.getMessage());
        }
        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                String data = "";
                switch (line.charAt(1)) {
                case 'T':
                    data = "todo " + line.substring(7);
                    taskList.createTodo(data);
                    break;
                case 'D':
                    data = "deadline " + line.substring(7);
                    data = data.replace("(", "/");
                    data = data.replace(")", "");
                    data = data.replace(":", "");
                    taskList.createDeadline(data);
                    break;
                case 'E':
                    data = "event " + line.substring(7);
                    data = data.replace("(from:", "/from");
                    data = data.replace(")", "");
                    data = data.replace("to:", "/to");
                    System.out.println(data);
                    taskList.createEvent(data);
                    break;
                }
                if (line.charAt(4) == 'X') {
                    taskList.markTask(count);
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        System.out.println("Finish loading data\n");
    }

    /**
     * get number of tasks in taskList
     */
    public static int getCount(){
        return count;
    }

    /**
     * Increment number of tasks in taskList
     */
    public static void incrementCount(){
        count++;
    }

    /**
     * Decrement number of tasks in taskList
     */
    public static void decrementCount(){
        count--;
    }

    /**
     * Save taskList in file in filePath
     */
    public static void saveFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < count; ++i) {
                fw.write(TaskList.getTask(i)+ System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong during saving changes: " + e.getMessage());
        }
    }

    /**
     * get taskList stored
     */
    public TaskList getTasklist() {
        return taskList;
    }
}
