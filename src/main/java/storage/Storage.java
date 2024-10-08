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

    private Storage(Path pathToFile) throws InvalidFileException {
        if (!isValidFile(pathToFile)) {
            throw new InvalidFileException("No save file found!");
        }

        saveFilePath = pathToFile;
    }

    private Storage() { // Throws no exception because automatically valid
        saveFilePath = defaultsavePath;
    }

    public static Storage create(String pathToFile) throws InvalidFileException {
        return new Storage(processPath(pathToFile));
    }

    public static Storage create() {
        return new Storage();
    }

    public boolean isValidFile(Path pathToFile) {
        return pathToFile.toString().endsWith(".subot");
    }

    private static Path processPath(String path) throws InvalidFileException {
        Path p = Paths.get(path);

        if (!p.isAbsolute()) { p = Paths.get(workingDir, path); }
        if (p.toFile().isDirectory()) {
            throw new InvalidFileException("Must be a path to a file!");
        }
        return p;
    }

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
