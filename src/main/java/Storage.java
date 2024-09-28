import task.Deadline;
import task.Event;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void writerSetUp(String filePath) throws IOException {
        File listFile = new File(filePath);
        if (!listFile.exists()) {
            File directory = listFile.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }
            listFile.createNewFile(); // Create file if it doesn't exist
        }
    }

    public static void loadDataFromFile(String filePath, List userList) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseAndAddItem(line, userList);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }

    private static void parseAndAddItem(String line, List userList) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0]; // T, D, E
        boolean isDone = parts[1].equals("X");
        String taskDescription = parts[2];


        switch (taskType) {
        case "T":
            Todo todoTask = new Todo(taskDescription);
            if (isDone) {
                todoTask.markAsDone();
            }
            userList.itemArrayList.add(todoTask);
            break;

        case "D":
            String deadlineDate = parts[3]; // Ignore remaining parts
            Deadline deadlineTask = new Deadline(taskDescription, deadlineDate);
            if (isDone) {
                deadlineTask.markAsDone();
            }
            userList.itemArrayList.add(deadlineTask);
            break;

        case "E":
            String eventStart = parts[3]; // Start date/time
            String eventEnd = parts[4];   // End date/time
            Event eventTask = new Event(taskDescription, eventStart, eventEnd);
            if (isDone) {
                eventTask.markAsDone();
            }
            userList.itemArrayList.add(eventTask);
            break;

        default:
            System.out.println("Invalid task type in file: " + taskType);
            break;
        }
    }

    public static void saveListToFile(String listFilePath, List userList) {
        try {
            writeToFile(listFilePath, userList.getFormattedTasks()); // getFormattedTasks returns a formatted String
        } catch (IOException e) {
            System.out.println("An error occurred while saving the list.");
        }
    }
}

