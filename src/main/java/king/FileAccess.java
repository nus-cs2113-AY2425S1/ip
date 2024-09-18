package king;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static king.King.tasks;
import static king.King.tasksCount;

public class FileAccess {
    private final static String defaultFilePath = "out/production/ip/king/Tasks.txt";
    private final static String backupFilePath = "src/Tasks_backup.txt";

    protected static boolean checkFile() {
        File f = new File(defaultFilePath);
//        f.getAbsolutePath();
//        f.isDirectory();
        return f.exists();
    }

    protected static void createNewFile() throws IOException {
        Files.copy(Paths.get(backupFilePath), Paths.get(defaultFilePath));
    }

    protected static void deleteFile() throws IOException, KingException {
        try {
            Files.delete(Paths.get(defaultFilePath));
        } catch (IOException e) {
            throw new KingException("You do not have any file to delete...");
        }
    }

    protected static ArrayList<Task> readFile() throws IOException {
        if (!checkFile()) {
            createNewFile();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(defaultFilePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String[] taskDetails;

        while (s.hasNext()) {
            String task = s.nextLine();
            taskDetails = task.split("\\|");

            if (taskDetails[0].equals("[T]")) {
                Todo t = new Todo(taskDetails[2]);
                t.isDone = taskDetails[1].equals("true");
                tasks.add(t);

            } else if (taskDetails[0].equals("[D]")) {
                Deadline d = new Deadline(taskDetails[2], taskDetails[4]);
                d.isDone = taskDetails[1].equals("true");
                tasks.add(d);

            } else if (taskDetails[0].equals("[E]")) {
                Event e = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                e.isDone = taskDetails[1].equals("true");
                tasks.add(e);
            }

        }
        return tasks;
    }

    protected static void updateFile() {
        try {
            deleteFile();
            createNewFile();
            for (int i = 0; i < tasksCount; i++) {
                Task task = tasks.get(i);
                if (task instanceof Todo) {
                    Todo todoTask = (Todo) task;
                    appendToFile(defaultFilePath, todoTask.TASKTYPEICON + "|"
                                                  + todoTask.isDone + "|"
                                                  + todoTask.description + "|"
                                                  + System.lineSeparator());
                } else if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    appendToFile(defaultFilePath, deadlineTask.TASKTYPEICON + "|"
                                                  + deadlineTask.isDone + "|"
                                                  + deadlineTask.description + "|"
                                                  + deadlineTask.taskStartTime + "|"
                                                  + deadlineTask.taskEndTime + System.lineSeparator());
                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    appendToFile(defaultFilePath, eventTask.TASKTYPEICON + "|"
                                                  + eventTask.isDone + "|"
                                                  + eventTask.description + "|"
                                                  + eventTask.taskStartTime + "|"
                                                  + eventTask.taskEndTime + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (KingException e) {
            throw new RuntimeException(e);
        }
    }




    protected static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}




