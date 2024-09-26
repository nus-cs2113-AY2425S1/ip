import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Method to load tasks from the file
    public ArrayList<Task> loadTasks() throws FileNotFoundException{
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;

                switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "D":
                    task = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "E":
                    task = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case " ":
                    task = new Task(parts[2]);
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                }
            }
            sc.close();
        }

        return tasks;
    }

    // Method to save tasks to the file
    public void saveTasks(TaskList tasks) throws IOException {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // Create the directory if it doesn't exist
        }

        FileWriter fw = new FileWriter(file);
        for (Task task : tasks.getAllTasks()) {
            fw.write(task.toSaveFormat() + "\n");
        }
        fw.close();
    }
}
