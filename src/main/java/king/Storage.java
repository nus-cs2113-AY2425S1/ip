package king;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage of tasks for King.
 * Manages reading, writing, and deleting tasks from the storage file.
 */
public class Storage {
    private final static String DEFAULTFILEPATH = "out/production/ip/king/Tasks.txt";
    private final static String BACKUPFILEPATH = "src/main/resources/Tasks_backup.txt";

    /**
     * Checks if the storage file exists at the default file path.
     *
     * @return true if the file exists, false otherwise.
     */
    protected static boolean checkFile() {
        File f = new File(DEFAULTFILEPATH);
        return f.exists();
    }

    /**
     * Checks if the storage file is empty.
     *
     * @return true if the file is empty, false otherwise.
     */
    protected static boolean isEmptyFile() {
        File f = new File(DEFAULTFILEPATH);
        if (f.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Creates a new file by copying from a backup file. If the backup file is not found,
     * load the file from the resources and create a new file at the default path.
     *
     * @throws IOException If an I/O error occurs during file creation.
     */
    protected static void createNewFile() throws IOException {
        try {
            Files.copy(Paths.get(BACKUPFILEPATH), Paths.get(DEFAULTFILEPATH));
        } catch (Exception e) {
            InputStream backupFileStream = Storage.class.getClassLoader().getResourceAsStream("Tasks_backup.txt");
            File targetFile = new File(DEFAULTFILEPATH);
            targetFile.getParentFile().mkdirs();
            try (FileOutputStream out = new FileOutputStream(targetFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = backupFileStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    /**
     * Deletes the storage file if it exists. If the file does not exist, it throws an exception.
     *
     * @throws KingException If no file exists to delete.
     */
    public static void deleteFile() throws KingException {
        try {
            Files.deleteIfExists(Paths.get(DEFAULTFILEPATH));
            System.out.println("\n____________________________________________________________\n" +
                               " Saved file has been deleted!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new KingException("You do not have any file to delete...\n");
        }
    }

    /**
     * Reads tasks from the storage file and loads them into a list of tasks.
     * If the file is not found or is corrupted, it attempts to create a new file.
     *
     * @return An ArrayList<Task> of tasks loaded from the file.
     * @throws IOException   If an I/O error occurs during file reading.
     * @throws KingException If the file is corrupted or the data is invalid.
     */
    protected static ArrayList<Task> readFile() throws IOException, KingException {
        if (!checkFile()) {
            createNewFile();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(DEFAULTFILEPATH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try (Scanner s = new Scanner(f)) {
            String[] taskDetails;

            while (s.hasNext()) {
                String task = s.nextLine();
                taskDetails = task.split("\\|");

                if (taskDetails[0].equals("[T]")) {
                    Todo t = new Todo(taskDetails[2].trim());
                    t.isDone = taskDetails[1].trim().equals("true");
                    tasks.add(t);

                } else if (taskDetails[0].equals("[D]")) {
                    String description = taskDetails[2].trim();
                    String deadlineStr = taskDetails[3].trim();
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineStr, formatter);
                    Deadline d = new Deadline(description, deadlineDateTime);
                    d.isDone = taskDetails[1].trim().equals("true");
                    tasks.add(d);

                } else if (taskDetails[0].equals("[E]")) {
                    String description = taskDetails[2].trim();
                    String startTimeStr = taskDetails[3].trim();
                    String endTimeStr = taskDetails[4].trim();
                    LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
                    LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);
                    Event e = new Event(description, startTime, endTime);
                    e.isDone = taskDetails[1].trim().equals("true");
                    tasks.add(e);
                }
            }
        } catch (Exception e) {
            deleteFile();
            createNewFile();
            throw new KingException(" The saved file was corrupted!\n");
        }
        return tasks;
    }

    /**
     * Updates the storage file with the current task list. Writes each task's details to the file.
     */
    protected static void updateFile() {
        try {
            try (FileWriter fw = new FileWriter(DEFAULTFILEPATH, false)) {
                for (Task task : TaskList.getTasks()) {
                    if (task instanceof Todo) {
                        Todo todoTask = (Todo) task;
                        fw.write(todoTask.TASKTYPEICON + "|"
                                 + todoTask.isDone + "|"
                                 + todoTask.description + "\n");
                    } else if (task instanceof Deadline) {
                        Deadline deadlineTask = (Deadline) task;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        fw.write(deadlineTask.TASKTYPEICON + "|"
                                 + deadlineTask.isDone + "|"
                                 + deadlineTask.getDescription() + "|"
                                 + deadlineTask.getDeadlineDateTime().format(formatter) + "\n");
                    } else if (task instanceof Event) {
                        Event eventTask = (Event) task;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        fw.write(eventTask.TASKTYPEICON + "|"
                                 + eventTask.isDone + "|"
                                 + eventTask.getDescription() + "|"
                                 + eventTask.getStartTime().format(formatter) + "|"
                                 + eventTask.getEndTime().format(formatter) + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
