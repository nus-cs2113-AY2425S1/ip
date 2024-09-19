package cristiano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks (goals) to a file.
     */
    public void saveGoals(List<Goal> goals) throws IOException {
        File file = new File(filePath);

        // Ensure the parent directories exist
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("Failed to create the directory: " + parentDir);
        }

        // Print the absolute file path for debugging
        System.out.println("Saving data at: " + file.getAbsolutePath() +"\n");


        // Write each goal to the file
        FileWriter writer = new FileWriter(file);
        for (Goal goal : goals) {
            writer.write(goal.toFileFormat() + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Loads tasks (goals) from a file.
     */
    public List<Goal> loadGoals() throws FileNotFoundException {
        File file = new File(filePath);
        List<Goal> goals = new ArrayList<>();

        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                goals.add(Goal.fromFileFormat(line));
            }
            scanner.close();
        }

        return goals;
    }
}