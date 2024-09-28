package medea.core;

import medea.exceptions.MedeaException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String path;

    public Storage(String path){
        this.path = path;
    }

    public String loadTasks(){
        try{
            return getTasksFromFile();
        }
        catch(FileNotFoundException e){
            throw new MedeaException("Failed to load tasks from disk: " + e.getMessage());
        }
    }

    public String getTasksFromFile() throws FileNotFoundException {
        StringBuilder tasks = new StringBuilder();
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()){
            tasks.append(scanner.nextLine());
            tasks.append("\n");
        }

        scanner.close();
        return tasks.toString();
    }

    public void saveTasks(String tasksCSVString){
        try{
            writeTasksToFile(tasksCSVString);
        }catch(IOException e){
            throw new MedeaException("Failed to save tasks to disk: " + e.getMessage());
        }
    }

    private void writeTasksToFile(String csvString) throws IOException {
        createFileIfNotExists();
        FileWriter fileWriter =  new FileWriter(path);
        fileWriter.write(csvString);
        fileWriter.close();
    }

    public void createFileIfNotExists() throws IOException {
        File file = new File(path);
        if (file.exists()) return;

        File parentDir = file.getParentFile();

        //Create directory if it does not exist
        if (parentDir != null && !parentDir.exists()) {
            boolean mkDirResult = parentDir.mkdirs();
            if (!mkDirResult){
                throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }

        //Create file
        boolean mkFileResult = file.createNewFile();
        if (!mkFileResult){
            throw new IOException("Failed to create file: " + file.getAbsolutePath());
        }
    }
}