package hsien.storage;

import hsien.task.Task;
import hsien.exception.HsienException;
import hsien.task.Deadline;
import hsien.task.Event;
import hsien.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the storage and retrieval of tasks from a specified file.
 */
public class Storage {
    private final String filepath;

    /**
     * Handles the storage and retrieval of tasks from a specified file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads tasks from the specified file and adds them to the provided list.
     *
     * @param tasks the list to which tasks will be added
     */
    public void readFile(ArrayList<Task> tasks) {
        try {
            File f = new File(filepath);
            Scanner line = new Scanner(f);
            System.out.println("Adding tasks...");
            while (line.hasNext()) {
                String[] processedLine = processFileLine(line.nextLine().replace("\n", ""));
                addTaskFromFile(processedLine[0], processedLine[1], processedLine[2], tasks);
            }
            System.out.printf("You have [%d] tasks in the list.%n", tasks.size());
            System.out.println("Finished adding tasks from file");
        } catch (FileNotFoundException e) {
            // Handle the case where the file does not exist
            System.out.println("File not found");
        } catch (HsienException e) {
            System.out.println("Incorrect file input");
        }
    }

    /**
     * Writes the current list of tasks to the specified file.
     *
     * @param tasks the list of tasks to be written to the file
     */
    public void writeFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filepath);
            for (Task task: tasks) {
                fw.write(task.getStatusDescription() + "\n");
            }
            System.out.println("Tasks saved successfully!");
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    /**
     * Processes a line from the file and extracts the command, mark status, and description.
     *
     * @param input the line to be processed
     * @return an array containing the command, mark status, and description
     * @throws HsienException if the input format is invalid
     */
    public static String[] processFileLine(String input) throws HsienException{
        String[] parts = input.split(" ");
        System.out.println(input);
        String regex = "\\[(.*?)\\] \\[(.*?)\\] (.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input.trim());

        if (matcher.matches()) {
            String command = matcher.group(1); // Extract command from the first group
            String isMark = matcher.group(2); // Extract mark status from the second group
            String desc = matcher.group(3); // Extract description from the third group
            return new String[]{command, isMark, desc};
        } else {
            throw new HsienException();
        }
    }

    /**
     * Adds a task to the list based on the provided command, mark status, and description.
     *
     * @param command the command indicating the task type
     * @param isMark  the mark status of the task
     * @param desc    the description of the task
     * @param tasks   the list to which the task will be added
     */
    public static void addTaskFromFile(String command, String isMark, String desc, ArrayList<Task> tasks) {
        Task newTask = null;
        String tempDesc;

        // Create Task object based on command
        if (command.equals("T")) {
            newTask = new Todo(desc);
        } else if (command.equals("D")) {
            tempDesc = desc.split("\\(by:")[0].trim();
            String byDate = desc.split("\\(by:")[1].trim();
            newTask = new Deadline(tempDesc, byDate);
        } else {
            tempDesc = desc.split("\\(from:")[0].trim();
            String[] dates = desc.split("\\(from:")[1].split("to:");
            String fromDate = dates[0].trim();
            String toDate = dates[1].substring(0, dates[1].length()-1).trim();
            newTask = new Event(tempDesc, fromDate, toDate);
        }

        if (isMark.equals("X")) {
            newTask.mark();
        }

        tasks.add(newTask);
    }

}
