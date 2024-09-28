package main.java;

import ran.task.Deadline;
import ran.task.Event;
import ran.task.Todo;
import main.java.TaskList;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Storage {
    private String filePath;
    private File dataFile;

    public Storage(String directory) throws IOException {
        filePath = directory + "/ran.txt";
        // Check for existence of directory and create it if it doesn't exist yet
        File dir = new File(directory);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        // Check for existence of data file and create it if it doesn't exist yet
        dataFile = new File(filePath);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
    }

    public void loadTasks(TaskList tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(dataFile);
        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] taskInstruction = task.split(", ");
            boolean isDone = taskInstruction[1].equals("1");
            if (taskInstruction[0].equals("T")) {
                tasks.addTask(new Todo(isDone, taskInstruction[2]));
            } else if (taskInstruction[0].equals("D")) {
                tasks.addTask(new Deadline(isDone, taskInstruction[2], taskInstruction[3]));
            } else if (taskInstruction[0].equals("E")) {
                tasks.addTask(new Event(isDone, taskInstruction[2], taskInstruction[3], taskInstruction[4]));
            }
        }
    }
    
    public void addToDataFile(String input) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(input + System.lineSeparator());
        fw.close();
    }

    public String getDataFileContent() throws FileNotFoundException {
        Scanner sc = new Scanner(dataFile);
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNext()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String dataFileContent = buffer.toString();
        sc.close();
        return dataFileContent;
    }

    public void modifyDataFile(String oldLine, String newLine) throws IOException {
        String dataFileContent = getDataFileContent();
        String newDataFileContent = dataFileContent.replaceAll(oldLine, newLine);
        FileWriter fw = new FileWriter(filePath);
        fw.write(dataFileContent);
        fw.close();
    }
    
    public void deleteFromDataFile(String line) throws IOException {
        String dataFileContent = getDataFileContent();
        String newDataFileContent = dataFileContent.replaceAll(line + System.lineSeparator() , "");
        FileWriter fw = new FileWriter(filePath);
        fw.write(dataFileContent);
        fw.close();
    }
}
