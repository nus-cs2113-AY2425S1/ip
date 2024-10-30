import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * Manages saving and loading tasks to/from a file.
 */
public class Storage {
    private static final String FILE_PATH = "data/duke.txt";

    /**
     * This class saves the tasks inputted by the user.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
                writer.write(task.toSave() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    /**
     * This class loads the tasks stored in the file path.
     *
     * @return A list of tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
//            System.out.println("No previous tasks found. Please create a new task list.");
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                for (int i = 0; i< parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                switch(parts[0]) {
                    case "T":
                        Todo todo = new Todo(parts[2]);
                        if (parts[1].equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline ddl = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) {
                            ddl.markAsDone();
                        }
                        tasks.add(ddl);
                        break;
                    case "E":
                        Event event = new Event(parts[2], parts[3], parts[4]);
                        if (parts[1].equals("1")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
        }

        return tasks;
    }
}
