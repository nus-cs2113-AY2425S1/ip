package UserInteraction;

import TaskTypes.Deadline;
import TaskTypes.Event;
import TaskTypes.Task;
import TaskTypes.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    //private static final String FILE_PATH = "data/listOfTasks.txt";
    private final String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> initializeArrayList() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>(); //before return statement it said tasks is never queried
        Scanner s = new Scanner(new File(filePath));
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
        return tasks;
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
                int indexOfHyphen = storableString.indexOf('-');
                String from = storableString.substring(indexOfThirdStandingLine + 2, indexOfHyphen - 1);
                String to = storableString.substring(indexOfHyphen + 2);
                return new Event(description, from, to, isDone);
            default:
                System.out.println("Invalid task type");
                return null;
        }
    }

    public void rewriteArrayList(ArrayList<Task> tasks) {
        try {
            writeToFile(filePath, "");
            for (Task task : tasks) {
                appendToFile(filePath, task.getStorableString());
            }
            //initializeArrayList(tasks, new File(filePath));
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }

    public void addTaskToFile(Task task) {
        try {
            appendToFile(filePath, task.getStorableString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static void getFileProperties(File f) {
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }
}

/* Testing
        File f = new File(FILE_PATH);
        getFileProperties(f);
        String absolutePath = f.getAbsolutePath();

        try {
            printFileContents(absolutePath, f);
            Ui edith = new Ui();
            initializeArrayList(edith.getTasks(), f);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("I/O exception.");
        }
 */