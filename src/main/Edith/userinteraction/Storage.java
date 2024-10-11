package userinteraction;

import tasktypes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with all operations relating to reading and writing to/from the file.
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
     * Returns the list of tasks that was read from the file. It converts tasks one by one
     * from the storable format to the required form to be used by the program.
     * It collects all these tasks, which is eventually returned.
     *
     *
     * @return The list of tasks that was read from the file
     * @throws IOException If reading from the file is not possible (e.g. when file does not exist)
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
     * Returns a Task, which is created by interpreting the storable format
     * of the task and using those details to create an object of the class Task
     *
     * @param storableString The user input
     * @return The Task which is created
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
     * Used to completely rewrite the list of tasks in the file.
     * It does so by first erasing the existing task from the file
     * and then adding each task present in the list of tasks in the
     * program to the file (by first converting each task to a storable format).
     * @param tasks The current list of tasks
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
     * Adds a Task to the file by first converting it to
     * a storable format and then appending to the file.
     *
     * @param task The current list of tasks
     */
    public void addTaskToFile(Task task) {
        try {
            appendToFile(filePath, task.getStorableString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes the received string to the file.
     *
     * @param filePath The relative path of the file
     * @param textToAdd The data to be written to the file
     * @throws IOException If the fileWriter object is not able to write to the file(e.g. when file is not present)
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends the received string to the file.
     *
     * @param filePath The relative path of the file
     * @param textToAppend The data to be appended to the file
     * @throws IOException If the fileWriter object is not able to write to the file(e.g. when file is not present)
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    private static void getFileProperties(File f) {
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }

    /**
     * Returns an Event object by interpreting the stored data
     * and creating an Event object based on that data
     *
     * @param storableString The details of the task in the stored format
     * @param indexOfSecondStandingLine The index of the second vertical bar
     * @param isDone The completion status of the Task
     * @return The created Event object
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
     * Returns a Deadline object by interpreting the stored data
     * and creating a Deadline object based on that data
     *
     * @param storableString The details of the task in the stored format
     * @param indexOfSecondStandingLine The index of the second vertical bar
     * @param isDone The completion status of the Task
     * @return The created Deadline object
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
     * Returns a ToDo object by interpreting the stored data
     * and creating an Todo object based on that data
     *
     * @param storableString The details of the task in the stored format
     * @param indexOfSecondStandingLine The index of the second vertical bar
     * @param isDone The completion status of the Task
     * @return The created ToDo object
     */
    public static ToDo createNewToDo(String storableString, int indexOfSecondStandingLine, boolean isDone) {
        String description = storableString
                .substring(indexOfSecondStandingLine + nextToNextCharacter);
        return new ToDo(description, isDone);
    }
}
