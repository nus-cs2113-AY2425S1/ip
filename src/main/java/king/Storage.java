package king;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final static String defaultFilePath = "out/production/ip/king/Tasks.txt";
    private final static String backupFilePath = "resources/Tasks_backup.txt";

    protected static boolean checkFile() {
        File f = new File(defaultFilePath);
        return f.exists();
    }

    protected static void createNewFile() throws IOException {
        try {
            Files.copy(Paths.get(backupFilePath), Paths.get(defaultFilePath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            InputStream backupFileStream = Storage.class.getClassLoader().getResourceAsStream("Tasks_backup.txt");
            File targetFile = new File(defaultFilePath);
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

    public static void deleteFile() throws IOException, KingException {
        try {
            Files.deleteIfExists(Paths.get(defaultFilePath));
            System.out.println("\n____________________________________________________________\n" +
                               " Saved file has been deleted!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new KingException("You do not have any file to delete...\n");
        }
    }

    protected static ArrayList<Task> readFile() throws IOException, KingException {
        if (!checkFile()) {
            createNewFile();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(defaultFilePath);
        try (Scanner s = new Scanner(f)) {
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
        } catch (Exception e) {
            deleteFile();
            createNewFile();
            throw new KingException("The saved file is corrupted! Clearing the file now...");
        }
        return tasks;
    }

    protected static void updateFile() {
        try {
            try (FileWriter fw = new FileWriter(defaultFilePath, false)) {
                for (Task task : TaskList.getTasks()) {
                    if (task instanceof Todo) {
                        Todo todoTask = (Todo) task;
                        fw.write(todoTask.TASKTYPEICON + "|"
                                 + todoTask.isDone + "|"
                                 + todoTask.description + "\n");
                    } else if (task instanceof Deadline) {
                        Deadline deadlineTask = (Deadline) task;
                        fw.write(deadlineTask.TASKTYPEICON + "|"
                                 + deadlineTask.isDone + "|"
                                 + deadlineTask.description + "|"
                                 + deadlineTask.taskStartTime + "|"
                                 + deadlineTask.taskEndTime + "\n");
                    } else if (task instanceof Event) {
                        Event eventTask = (Event) task;
                        fw.write(eventTask.TASKTYPEICON + "|"
                                 + eventTask.isDone + "|"
                                 + eventTask.description + "|"
                                 + eventTask.taskStartTime + "|"
                                 + eventTask.taskEndTime + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
