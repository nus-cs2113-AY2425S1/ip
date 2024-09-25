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
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final Path saveFile;

    public Storage(Path saveFile) {
        this.saveFile = saveFile;
    }

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
                    taskList.add(new Deadline(taskDetails[2].trim(), taskDetails[3].trim()));
                    taskList.get(index).setDone(taskDetails[1].trim().equals("1"));
                    break;
                case "E":
                    taskList.add(new Event(taskDetails[2].trim(), taskDetails[3].trim(), taskDetails[4].trim()));
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

    public void createFile(File file) throws AlyException {
        System.out.println("Searching for " + saveFile + "...");
        try {
            if (file.createNewFile()) {
                System.out.println("Cannot find file so I help you create already! File name: " + saveFile);
            } else {
                System.out.println("File already exists, I will edit that!");
            }
        } catch (IOException e) {
            throw new AlyException("Error! File sucks because: " + e.getMessage());
        }
    }

    public void createDirectory() throws AlyException {
        Path directory = saveFile.getParent(); // Get the parent directory
        System.out.println("Searching for directory: " + directory);
        if (directory != null && !Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new AlyException("Idk why cannot make directory siah: " + e.getMessage());
            }
            System.out.println("Directory didn't exist but I made it for u liao! You're welcome!");
        } else {
            System.out.println("Directory already exists, I will check in there!");
        }
    }
}
