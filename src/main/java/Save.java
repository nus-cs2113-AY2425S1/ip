import task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Save {

    public static void saveFile() {
        try {
            File f = new File("./src/main/java/tasks.txt");
            if (!f.exists()) {
                // file does not exist
                f.createNewFile();
            }

            // writing to the file
            // opening a file using FileWriter automatically overwrites its preexisting content
            FileWriter writer = new FileWriter("./src/main/java/tasks.txt");
            for (Task task : XiaoMe.tasks) {
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
            System.out.println("An error occurred while saving file.");
        }
    }

    public static void readFile() {
        try {
            File f = new File("./src/main/java/tasks.txt");
            if (!f.exists()) {
                // file does not exist
                f.createNewFile();
            }

            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\|");
                if (Objects.equals(words[0], "T")) {
                    XiaoMe.tasks.add(new Todo(words[2]));
                } else if (Objects.equals(words[0], "D")) {
                    XiaoMe.tasks.add(new Deadline(words[2], words[3]));
                } else if (Objects.equals(words[0], "E")) {
                    XiaoMe.tasks.add(new Event(words[2], words[3], words[4]));
                }

                if (Objects.equals(words[1], "X")) {
                    XiaoMe.tasks.getLast().setDone(true);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading file.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File data is corrupted");
        }
    }

}
