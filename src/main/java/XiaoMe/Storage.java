package XiaoMe;

import XiaoMe.task.Deadline;
import XiaoMe.task.Event;
import XiaoMe.task.Task;
import XiaoMe.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * The Storage class is responsible for saving and loading tasks from a file.
 */
public class Storage {

    static final String FILE_PATH = "./data/tasks.txt";
    static final String DIR_PATH = "./data";

    /**
     * Saves the given list of tasks to the specified file.
     *
     * @param tasks the list of tasks to save
     * @throws XiaoMeException if an error occurs while saving the file
     */
    public static void saveFile(ArrayList<Task> tasks) throws XiaoMeException {
        try {
            File dir = new File(DIR_PATH);
            if (!dir.isDirectory()) {
                // file does not exist
                dir.mkdir();
            }

            // writing to the file
            // opening a file using FileWriter automatically overwrites its preexisting content
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    // T|<icon>|<description>
                    writer.write(task.saveString());
                } else if (task instanceof Deadline) {
                    // D|<icon>|<description>|<by>
                    writer.write(task.saveString());
                } else if (task instanceof Event){
                    // E|<icon>|<description>|<start>|<end>
                    writer.write(task.saveString());
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new XiaoMeException("An error occurred while saving file.");
        }
    }

    /**
     * Reads tasks from the specified file and returns them as an ArrayList.
     * If the file does not exist, a new file is created.
     *
     * @return an ArrayList of tasks loaded from the file
     * @throws XiaoMeException if an error occurs while reading the file
     */
    public ArrayList<Task> readFile() throws XiaoMeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File f = new File(FILE_PATH);

            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\|");
                if (Objects.equals(words[0], "T")) {
                    tasks.add(new Todo(words[2]));
                } else if (Objects.equals(words[0], "D")) {
                    tasks.add(new Deadline(words[2], words[3]));
                } else if (Objects.equals(words[0], "E")) {
                    tasks.add(new Event(words[2], words[3], words[4]));
                } else {
                    throw new XiaoMeException("File data is corrupted");
                }

                if (Objects.equals(words[1], "X")) {
                    tasks.get(tasks.size() - 1).setDone(true);
                }
            }
            return tasks;

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new XiaoMeException("File data is corrupted");
        }
    }
}
