import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/KBot.txt";

    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Create directories if they do not exist
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    // You can also add a load method if needed in the future
}
