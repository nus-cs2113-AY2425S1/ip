package tommi;

import tommi.Task.Deadline;
import tommi.Task.Event;
import tommi.Task.Task;
import tommi.Task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    public static String FILE_PATH = "data/task-data.txt";

    /**
     * Print all tasks in the save file and
     * update the current TaskList with the save file data
     *
     * @throws FileNotFoundException If error accessing save file
     */
    public static void loadTaskData() throws FileNotFoundException{
        printSaveFile();
        updateTaskList();
    }

    /**
     * Clear the current save file and update it with the current TaskList
     *
     * @param taskList current TaskList
     * @throws IOException If file cannot be opened
     */
    public static void saveTaskData(ArrayList<Task> taskList) throws IOException{
        clearSaveFile();
        updateSaveFile(taskList);
    }

    /**
     * Clear the current save file
     *
     * @throws IOException If file cannot be opened
     */
    public static void clearSaveFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("");
        fw.close();
    }

    /**
     * Update the current save file with the current TaskList
     *
     * @param taskList current TaskList
     * @throws IOException If file cannot be opened
     */
    public static void updateSaveFile(ArrayList<Task> taskList) throws IOException {
        for (Task task : taskList) {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(task.toString() + "\n");
            fw.close();
        }
    }

    /**
     * Print the entire current save file using Scanner
     *
     * @throws FileNotFoundException If file cannot be opened
     */
    public static void printSaveFile() throws FileNotFoundException{
        // assign file to object
        File taskDataFile = new File(FILE_PATH);
        // use scanner to print file contents
        Scanner s = new Scanner(taskDataFile);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Update the current TaskList with the save file contents
     *
     * @throws FileNotFoundException If file cannot be opened
     * @throws IllegalArgumentException If the task type is not Deadline, Event or ToDo
     */
    public static void updateTaskList() throws FileNotFoundException{
        //for each line in text file
        File taskDataFile = new File(FILE_PATH);
        Scanner s = new Scanner(taskDataFile);
        while (s.hasNext()) {
            String task = s.nextLine();
            boolean isDone = checkIsDone(task);

            char taskType = task.charAt(1);
            switch (taskType) {
            case 'D':
                String[] deadlineParts = task.substring(7).split("\\(by:", 2);
                TaskList.addTaskWithoutMessage(new Deadline(isDone, deadlineParts[0],
                        deadlineParts[1].substring(0, deadlineParts[1].length() - 1)));
                break;
            case 'E':
                String[] eventParts = task.substring(7).split("\\(from:", 2);
                String[] timeParts = eventParts[1].split("to", 2);
                TaskList.addTaskWithoutMessage(new Event(isDone, eventParts[0], timeParts[0],
                        timeParts[1].substring(0, timeParts[1].length() - 1)));
                break;
            case 'T':
                String todoContent = task.substring(7);
                TaskList.addTaskWithoutMessage(new ToDo(isDone, todoContent));
                break;
            default:
                throw new IllegalArgumentException(
                        """
                                ____________________________________________________________
                                Error in save file formatting!
                                ____________________________________________________________"""
                );
            }
        }
    }

    /**
     * Check if a Task String from save file is done
     *
     * @param task Task that is represented as a string in the save file
     * @return boolean representing whether the task is done or not
     */
    public static boolean checkIsDone(String task) {
        return task.charAt(4) == 'X';
    }

    /**
     * Check if keyword is in any of the lines of the save file and
     * return an ArrayList of all the Strings that contain it
     *
     * @param keyword Keyword we are searching for
     * @return ArrayList of the Strings of tasks that contain the keyword
     * @throws FileNotFoundException If file cannot be opened
     */
    public static ArrayList<String> search(String keyword) throws FileNotFoundException{
        // go through task list and search for the word
        ArrayList<String> savedTaskList = new ArrayList<>();

        // return an arraylist of the tasks
        File taskDataFile = new File(FILE_PATH);
        Scanner s = new Scanner(taskDataFile);
        while (s.hasNext()) {
            String textLine = s.nextLine();
            if (textLine.contains(keyword)) {
                savedTaskList.add(textLine);
            }
        }
        return savedTaskList;
    }

}
