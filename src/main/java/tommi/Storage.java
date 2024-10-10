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

    public static void loadTaskData() throws FileNotFoundException{
        printSaveFile();
        updateTaskList();
    }

    public static void saveTaskData(ArrayList<Task> taskList) throws IOException{
        clearSaveFile();
        updateSaveFile(taskList);
    }

    public static void clearSaveFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("");
        fw.close();
    }

    public static void updateSaveFile(ArrayList<Task> taskList) throws IOException {
        for (Task task : taskList) {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(task.toString() + "\n");
            fw.close();
        }
    }

    public static void printSaveFile() throws FileNotFoundException{
        // assign file to object
        File taskDataFile = new File(FILE_PATH);
        // use scanner to print file contents
        Scanner s = new Scanner(taskDataFile);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

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
                        "____________________________________________________________\n"
                                + "Error in save file formatting!\n"
                                + "____________________________________________________________"
                );
            }
        }
    }

    public static boolean checkIsDone(String task) {
        return task.charAt(4) == 'X';
    }

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
