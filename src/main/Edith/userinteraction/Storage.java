package userinteraction;

import tasktypes.Deadline;
import tasktypes.Event;
import tasktypes.Task;
import tasktypes.ToDo;
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
        ArrayList<Task> tasks = new ArrayList<>();
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
        final int indexOfInt = 4;
        final int nextCharacter = 1;
        final int nextToNextCharacter = 2;

        char taskType = storableString.charAt(0);
        int isDoneInteger = Integer.parseInt(storableString
                .substring(indexOfInt, indexOfInt + nextCharacter));
        boolean isDone = isDoneInteger == 1;
        int indexOfSecondStandingLine = storableString.indexOf('|', indexOfInt + nextCharacter);
        String description;
        switch (taskType) {
            case 'T' :
                description = storableString
                        .substring(indexOfSecondStandingLine + nextToNextCharacter);
                return new ToDo(description, isDone);
            case 'D' :
                int indexOfThirdStandingLine = storableString
                        .indexOf('|', indexOfSecondStandingLine + nextCharacter);
                description = storableString
                        .substring(indexOfSecondStandingLine + nextToNextCharacter,
                                indexOfThirdStandingLine - nextCharacter);
                String by = storableString
                        .substring(indexOfThirdStandingLine + nextToNextCharacter);
                return new Deadline(description, by, isDone);
            case 'E' :
                indexOfThirdStandingLine = storableString
                        .indexOf('|', indexOfSecondStandingLine + nextCharacter);
                description = storableString
                        .substring(indexOfSecondStandingLine + nextToNextCharacter,
                                indexOfThirdStandingLine - nextCharacter);
                int indexOfHyphen = storableString.indexOf('-');
                String from = storableString
                        .substring(indexOfThirdStandingLine + nextToNextCharacter,
                                indexOfHyphen - nextCharacter);
                String to = storableString.substring(indexOfHyphen + nextToNextCharacter);
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
