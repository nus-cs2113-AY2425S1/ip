package UserInteraction;

import TaskTypes.Deadline;
import TaskTypes.Event;
import TaskTypes.Task;
import TaskTypes.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static File.FileFunctions.*;

public class Storage {
    private static final String FILE_PATH = "data/listOfTasks.txt";

    public static void initializeArrayList(ArrayList<Task> tasks, File f) throws IOException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String storableString = s.nextLine();
            Task task = stringToTask(storableString);
            if (task == null) {
                System.out.println("Invalid task type");
                continue;
            }
            tasks.add(task);
        }
        System.out.println("Successfully loaded tasks into the list");
    }

    public static Task stringToTask(String storableString) {
        char taskType = storableString.charAt(0);
        int isDoneInteger = Integer.parseInt(storableString.substring(4, 5));
        boolean isDone = isDoneInteger == 1;
        int indexOfSecondStandingLine = storableString.indexOf('|', 5);
        String description;
        switch (taskType) {
            case 'T' :
                description = storableString.substring(indexOfSecondStandingLine + 2);
                return new ToDo(description, isDone);
            case 'D' :
                int indexOfThirdStandingLine = storableString.indexOf('|', indexOfSecondStandingLine + 1);
                description = storableString.substring(indexOfSecondStandingLine + 2, indexOfThirdStandingLine - 1);
                String by = storableString.substring(indexOfThirdStandingLine + 2);
                return new Deadline(description, by, isDone);
            case 'E' :
                indexOfThirdStandingLine = storableString.indexOf('|', indexOfSecondStandingLine + 1);
                description = storableString.substring(indexOfSecondStandingLine + 2, indexOfThirdStandingLine - 1);
                int indexOfHiphen = storableString.indexOf('-');
                String from = storableString.substring(indexOfThirdStandingLine + 2, indexOfHiphen - 1);
                String to = storableString.substring(indexOfHiphen + 2);
                return new Event(description, from, to, isDone);
            default:
                System.out.println("Invalid task type");
                return null;
        }
    }
    public static void rewriteArrayList(ArrayList<Task> tasks) {

        try {
            writeToFile(FILE_PATH, "");
            for (Task task : tasks) {
                appendToFile(FILE_PATH, task.getStorableString());
            }
            //initializeArrayList(tasks, new File(FILE_PATH));
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }

    public static void addTaskToFile(Task task) {
        try {
            appendToFile(FILE_PATH, task.getStorableString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addTaskToArrayList(ArrayList<Task> tasks, Task task) throws IOException {
        tasks.add(task);
    }


    public static void deleteTaskFromFile(Task task) {
        File f = new File(FILE_PATH);
        String absolutePath = f.getAbsolutePath();
    }
}
