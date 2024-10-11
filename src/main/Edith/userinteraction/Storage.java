package userinteraction;

import tasktypes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class Storage {
    private final String filePath;
    private static final int indexOfInt = 4;
    private static final int nextCharacter = 1;
    private static final int nextToNextCharacter = 2;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return
     * @throws IOException
     */
    public ArrayList<Task> initializeArrayList() throws IOException, TaskTypeException {
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

    /**
     * @param storableString
     * @return
     */
    public static Task stringToTask(String storableString) throws TaskTypeException {


        char taskType = storableString.charAt(0);
        int isDoneInteger = Integer.parseInt(storableString
                .substring(indexOfInt, indexOfInt + nextCharacter));
        boolean isDone = isDoneInteger == 1;
        int indexOfSecondStandingLine = storableString.indexOf('|', indexOfInt + nextCharacter);
        switch (taskType) {
            case 'T' :
                return createNewToDo(storableString, indexOfSecondStandingLine, isDone);
            case 'D' :
                return createNewDeadline(storableString, indexOfSecondStandingLine, isDone);
            case 'E' :
                return createNewEvent(storableString, indexOfSecondStandingLine, isDone);
            default:
                throw new TaskTypeException("Invalid task type");
        }
    }

    /**
     * @param tasks
     */
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

    /**
     * @param task
     */
    public void addTaskToFile(Task task) {
        try {
            appendToFile(filePath, task.getStorableString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param filePath
     * @param textToAdd
     * @throws IOException
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * @param filePath
     * @param textToAppend
     * @throws IOException
     */
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

    /**
     * @param storableString
     * @param indexOfSecondStandingLine
     * @param isDone
     * @return
     */
    public static Event createNewEvent(String storableString, int indexOfSecondStandingLine, boolean isDone) {
        int indexOfThirdStandingLine = storableString
                .indexOf('|', indexOfSecondStandingLine + nextCharacter);
        String description = storableString
                .substring(indexOfSecondStandingLine + nextToNextCharacter,
                        indexOfThirdStandingLine - nextCharacter);
        int indexOfHyphen = storableString.indexOf('-');
        String from = storableString
                .substring(indexOfThirdStandingLine + nextToNextCharacter,
                        indexOfHyphen - nextCharacter);
        String to = storableString.substring(indexOfHyphen + nextToNextCharacter);
        return new Event(description, from, to, isDone);
    }

    /**
     * @param storableString
     * @param indexOfSecondStandingLine
     * @param isDone
     * @return
     */
    public static Deadline createNewDeadline(String storableString, int indexOfSecondStandingLine, boolean isDone) {
        int indexOfThirdStandingLine = storableString
                .indexOf('|', indexOfSecondStandingLine + nextCharacter);
        String description = storableString
                .substring(indexOfSecondStandingLine + nextToNextCharacter,
                        indexOfThirdStandingLine - nextCharacter);
        String by = storableString
                .substring(indexOfThirdStandingLine + nextToNextCharacter);
        return new Deadline(description, by, isDone);
    }

    /**
     * @param storableString
     * @param indexOfSecondStandingLine
     * @param isDone
     * @return
     */
    public static ToDo createNewToDo(String storableString, int indexOfSecondStandingLine, boolean isDone) {
        String description = storableString
                .substring(indexOfSecondStandingLine + nextToNextCharacter);
        return new ToDo(description, isDone);
    }
}
