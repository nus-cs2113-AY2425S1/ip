package ellio.storage;

import ellio.EllioExceptions;
import ellio.task.Deadline;
import ellio.task.Event;
import ellio.task.Todo;
import ellio.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static File saveFile;
    public static ArrayList<Task> listTasks;

    public Storage(String filePath) {
        saveFile = new File(filePath);
        if (!saveFile.exists()) {
            createFile();
        }
    }

    /**
     * Creates a New File with the predeclared filepath
     * Checks & ensures if the parent directory does not exist, or not null path.
     * else prints an error output.
     */
    public static void createFile(){
        try{
            File parentDir = saveFile.getParentFile();
            if(!parentDir.exists() && parentDir != null){
                parentDir.mkdirs();
            }
            saveFile.createNewFile();
        } catch (IOException e){
            System.out.println("Error Occured: " + e.getMessage());
        }
    }

    /**
     * Extracts data from the save file (txt format) and converts it into an ArrayList<Task> object
     * Iterates through the txt file line by line and adds
     * the corresponding task into the ArrayList
     * @return An ArrayList with the loaded content from save file
     * @throws EllioExceptions
     */
    public static ArrayList<Task> loadContent() throws EllioExceptions{
        listTasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(saveFile);
            while(s.hasNextLine()){
                String line = s.nextLine().trim();
                String[] parts = line.split(" \\| ");
                switch(parts[0]){
                case "t":
                    listTasks.add(new Todo(parts[2], parts[1]));
                    break;
                case "d":
                    listTasks.add(new Deadline(parts[2], parts[1], parts[3]));
                    break;
                case "e":
                    listTasks.add(new Event(parts[2], parts[1], parts[3], parts[4]));
                    break;
                default:
                    System.out.println("Error Reading File");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new EllioExceptions.ErrorLoadingFileException();
        }
        return listTasks;
    }

    /**
     * Appends a new task to the save file.
     * @param savedTask Description of the new task in txt format
     */
    public static void saveNewTask(String savedTask){
        try {
            FileWriter fw = new FileWriter(saveFile, true);
            fw.write( System.lineSeparator() + savedTask);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the new file by overwriting the old content with a new one.
     * After clearing the previous data, iterate through the list and append
     * to the new file one by one
     */
    public static void updateSavedTaskFile(){
        String updatedTasksForSaveFile = listTasks.get(0).getSaveFileTask();
        for(int i = 1; i < listTasks.size(); i++){
            updatedTasksForSaveFile += System.lineSeparator() + listTasks.get(i).getSaveFileTask();
        }
        try{
            FileWriter fw = new FileWriter(saveFile);
            fw.write(updatedTasksForSaveFile);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
