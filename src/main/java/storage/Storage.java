package storage;

import exceptions.XiaoMeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {

    public static void saveFile(ArrayList<Task> tasks) throws XiaoMeException {
        try {
            File f = new File("./tasks.txt");
            if (!f.exists()) {
                // file does not exist
                f.createNewFile();
            }

            // writing to the file
            // opening a file using FileWriter automatically overwrites its preexisting content
            FileWriter writer = new FileWriter("./tasks.txt");
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    // T|<icon>|<description>
                    writer.write("T|" + task.getStatusIcon() + "|" + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    // D|<icon>|<description>|<by>
                    writer.write("D|" + task.getStatusIcon() + "|" + task.getDescription() + "|"
                                 + ((Deadline) task).getBy() + "\n");
                } else if (task instanceof Event){
                    // E|<icon>|<description>|<start>|<end>
                    writer.write("E|" + task.getStatusIcon() + "|" + task.getDescription() + "|"
                                 + ((Event) task).getStart() + "|" + ((Event) task).getEnd() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new XiaoMeException("An error occurred while saving file.");
        }
    }

    public ArrayList<Task> readFile() throws XiaoMeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File f = new File("./tasks.txt");
            if (!f.exists()) {
                // file does not exist
                f.createNewFile();
            }

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
                }

                if (Objects.equals(words[1], "X")) {
                    tasks.get(tasks.size() - 1).setDone(true);
                }
            }

        } catch (IOException e) {
            throw new XiaoMeException("An error occurred while reading file.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new XiaoMeException("File data is corrupted");
        }
        return tasks;
    }
}
