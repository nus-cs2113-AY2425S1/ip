package AlyBot;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles reading and writing tasks to the file system.
 */
public class Storage {

    private final Path saveFile;

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param saveFile The path to the file for saving and loading tasks.
     */
    public Storage(Path saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Loads the task list from the save file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws AlyException If an error occurs during file reading.
     */
    public ArrayList<Task> load() throws AlyException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(saveFile.toString());
            Scanner scanner = new Scanner(file);
            int index = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskDetails = line.split("\\|");

                switch (taskDetails[0]) {
                case "T":
                    taskList.add(new Todo(taskDetails[2].trim()));
                    taskList.get(index).setDone(taskDetails[1].trim().equals("1"));
                    break;
                case "D":
                    LocalDateTime dueBy = LocalDateTime.parse(taskDetails[3].trim(), DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
                    String formattedDueBy = dueBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    taskList.add(new Deadline(taskDetails[2].trim(), formattedDueBy));
                    taskList.get(index).setDone(taskDetails[1].trim().equals("1"));
                    break;
                case "E":
                    LocalDateTime fromDate = LocalDateTime.parse(taskDetails[3].trim(), DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
                    LocalDateTime toDate =  LocalDateTime.parse(taskDetails[4].trim(), DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
                    String formattedFromDate = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    String formattedToDate = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    taskList.add(new Event(taskDetails[2].trim(), formattedFromDate, formattedToDate));
                    taskList.get(index).setDone(taskDetails[1].trim().equals("1"));
                    break;
                default:
                    throw new AlyException("Idk what task is this siah: " + taskDetails[0]);
                }
                index++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new AlyException("Your file dont have leh, maybe cos " + e.getMessage(), e);
        } catch (Exception e) {
            throw new AlyException("Error! Your file sucks because " + e.getMessage(), e);
        }
        return taskList;
    }

    /**
     * Writes the current task list to the save file.
     *
     * @param taskList The list of tasks to save.
     * @throws AlyException If an error occurs during file writing.
     */
    public void write(TaskList taskList) throws AlyException {
        try {
            FileWriter fileWriter = new FileWriter(saveFile.toString());

            for (Task task : taskList.getList()) {
                if (task != null) {
                    String toSave = task.toString();
                    char status = '0';
                    if (toSave.charAt(4) == 'X') {
                        status = '1';
                    }
                    char type = toSave.charAt(1);
                    switch (type) {
                    case 'T':
                    case 'D':
                    case 'E':
                        toSave = task.toFile(toSave, status);
                        break;
                    default:
                        throw new IllegalArgumentException();
                    }
                    fileWriter.write(toSave + System.lineSeparator());
                }
            }

            fileWriter.close();

            System.out.println("Tasks recorded successfully to " + saveFile);
        } catch (IOException e) {
            throw new AlyException("Error! Your file sucks because: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new AlyException("Idk what task is this siah, how to save??", e);
        } catch (Exception e) {
            throw new AlyException("Error! Your file sucks because " + e.getMessage(), e);
        }
    }

    /**
     * Creates a new save file if it does not already exist.
     *
     * @param file The file to create.
     * @throws AlyException If an error occurs during file creation.
     */
    public void createFile(File file) throws AlyException {
        System.out.println("Searching for " + saveFile + "...");
        try {
            if (file.createNewFile()) {
                System.out.println("Cannot find task data file so I help you create bro! File name: " + saveFile);
            } else {
                System.out.println("Loaded task data from " + saveFile + "!");
            }
        } catch (IOException e) {
            throw new AlyException("Error! File sucks because: " + e.getMessage());
        }
    }

    /**
     * Creates the directory for the save file if it does not already exist.
     *
     * @throws AlyException If an error occurs during directory creation.
     */
    public void createDirectory() throws AlyException {
        Path directory = saveFile.getParent();
        if (directory != null && !Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
                System.out.println("Cannot find directory so I help you create first!");
            } catch (IOException e) {
                throw new AlyException("Error creating directory: " + e.getMessage());
            }
        }
    }
}