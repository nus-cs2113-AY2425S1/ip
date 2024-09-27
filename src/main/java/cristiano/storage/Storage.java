package cristiano.storage;

import cristiano.goals.Goal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class that holds the storage to a data file and its file path
 * The class contains methods to help save, as well as load goals.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of current goals to a file.
     * This method is to be called everytime an addition or deletion of goal is made.
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
     * Loads goals from a file into a list.
     * Unknown goal types will not be added into the list.
     *
     * @return The list of saved goals
     */
    public List<Goal> loadGoals() throws FileNotFoundException {
        File file = new File(filePath);
        List<Goal> goals = new ArrayList<>();

        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Goal goal = Goal.fromFileFormat(line);
                if (goal != null) {
                    goals.add(goal);
                }
            }
            scanner.close();
        }

        return goals;
    }
}
