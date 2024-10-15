package storage;

import exception.InvalidFileException;
import task.Task;
import task.TaskList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private static final String defaultFileName = "tasks.subot";
    private static final String workingDir = System.getProperty("user.dir");
    private static final Path defaultsavePath = Paths.get(workingDir, defaultFileName);
    private static Path saveFilePath;

    /**
     * Constructor for {@link Storage Storage} object, access via {@link Storage#create(String) create(String)} method
     * @param pathToFile {@link Path Path} object to perform on
     * @throws InvalidFileException If no save file is found
     */
    private Storage(Path pathToFile) throws InvalidFileException {
        if (!isValidFile(pathToFile)) {
            throw new InvalidFileException("No save file found!");
        }

        saveFilePath = pathToFile;
    }

    /**
     * Default constructor for {@link Storage Storage} object, access via {@link Storage#create() create} method.
     * Note that this throws no exception because default path is automatically valid
     */
    private Storage() { // Throws no exception because automatically valid
        saveFilePath = defaultsavePath;
    }

    /**
     * Create a {@link Storage Storage} object with provided path
     * @param pathToFile Path to save file, treated as a <code>String</code>
     * @return {@link Storage Storage} object if successful
     * @throws InvalidFileException If path is a directory or file not found
     */
    public static Storage create(String pathToFile) throws InvalidFileException {
        return new Storage(processPath(pathToFile));
    }

    /**
     * Create a {@link Storage Storage} object with default path
     * @return {@link Storage Storage} object}
     */
    public static Storage create() {
        return new Storage();
    }

    /**
     * Validate path to see if it's a valid path to a save file
     * @param pathToFile Input path
     * @return <code>true</code> if the format is valid, <code>false</code> otherwise
     */
    public boolean isValidFile(Path pathToFile) {
        return pathToFile.toString().endsWith(".subot");
    }

    /**
     * Process through input <code>path</code> String to return an absolute path to file
     * @param path Input path as <code>String</code>
     * @return {@link Path Path} object represents the path to the file
     * @throws InvalidFileException If input String is not a file (i.e: a directory)
     */
    private static Path processPath(String path) throws InvalidFileException {
        Path p = Paths.get(path);

        if (!p.isAbsolute()) { p = Paths.get(workingDir, path); }
        if (p.toFile().isDirectory()) {
            throw new InvalidFileException("Must be a path to a file!");
        }
        return p;
    }

    /**
     * Load {@link TaskList TaskList} from a save file
     * @return {@link TaskList TaskList} retrieved from save file (if successful)
     * @throws InvalidFileException If no save file is found or fail to read
     * @throws IOException If an I/O error occurs while reading
     */
    public TaskList load() throws InvalidFileException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(saveFilePath.toFile())
            );
            ArrayList<?> savedTasks = (ArrayList<?>) ois.readObject();
            savedTasks.stream()
                    .filter(Objects::nonNull)
                    .forEach( task -> tasks.add((Task) task) );
            ois.close();
            System.out.println("Loaded " + tasks.size() + " tasks!");
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("No save file detected!");
        } catch (ClassNotFoundException e) {
            throw new InvalidFileException("Corrupted save file!");
        }
        return new TaskList(tasks);
    }

    /**
     * Save a {@link TaskList TaskList} to a save file
     * @param tasks A {@link TaskList TaskList} needs to be saved
     */
    public void save(TaskList tasks) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(saveFilePath.toFile())
            );
            oos.writeObject(tasks.getTasks());
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Failed to save tasks to file, retry? (y/n)");
            Scanner sc = new Scanner(System.in);
            char choice = sc.next().charAt(0);
            if (choice == 'y' | choice == 'Y') save(tasks);
        } finally {
            System.err.println("Tasks saved to \"" + saveFilePath.toFile() + "\"");
        }
    }
}
