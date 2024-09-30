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
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void readFile(ArrayList<Task> tasks) {
        try {
            File f = new File(filepath);
            Scanner line = new Scanner(f);
            while (line.hasNext()) {
                String[] processedLine = processFileLine(line.nextLine().replace("\n", ""));
                addTaskFromFile(processedLine[0], processedLine[1], processedLine[2], tasks);
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file does not exist
            System.out.println("File not found");
        } catch (HsienException e) {
            System.out.println("Incorrect file input");
        }
    }

    public void writeFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filepath);
            for (Task task: tasks) {
                fw.write(task.getStatusDescription() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

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

    public static void addTaskFromFile(String command, String isMark, String desc, ArrayList<Task> tasks) {
        Task newTask = null;
        String tempDesc;

        // Create Task object based on action
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

        // Mark task
        if (isMark.equals("X")) {
            newTask.mark();
        }

        tasks.add(newTask);
        System.out.println("Added task: " + newTask.getDescription());
        System.out.printf("Now you have [%d] tasks in the list.%n", tasks.size());
    }

}
