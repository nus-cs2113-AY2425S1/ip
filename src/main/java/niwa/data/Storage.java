package niwa.data;

import niwa.data.task.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage {
    private final File dataFile;

    public File getDataFile() {
        return dataFile;
    }

    public Storage(String fileName) {
        dataFile = new File(fileName);
    }

    public void createFile() throws IOException {
        if (dataFile.exists()) {
            throw new IOException("File exists.");
        }
        try {
            if (dataFile.getParentFile() != null && !dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new IOException("Can not clear the file: " + e.getMessage());
        }

    }

    public void clearFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException("File does not exist.");
        }
        try {
            new FileWriter(dataFile, false).close();
        } catch (IOException e) {
            throw new IOException("Failed to create the file: " + e.getMessage());
        }
    }

    public ArrayList<String> readFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException("Data file not found.");
        }
        if (dataFile.length() == 0) {
            throw new IOException("Empty file.");
        }
        return (ArrayList<String>) Files.readAllLines(dataFile.toPath(),
                Charset.defaultCharset());
    }

    public ArrayList<Task> parseTaskList(ArrayList<String> dataItems) {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (String line : dataItems) {
            Task temp = switch (line.charAt(0)) {
                case 'T' -> ToDo.parseTask(line);
                case 'D' -> Deadline.parseTask(line);
                case 'E' -> Event.parseTask(line);
                default -> null;
            };
            if(temp!=null) {
                allTasks.add(temp);
            }
        }
        return allTasks;
    }

    public void writeTaskList (ArrayList<Task> tasks) throws IOException {
        if (!dataFile.exists()) {
            createFile();
        } else {
            clearFile();
        }

        try (FileWriter writer = new FileWriter(dataFile, true)) {
            for (Task task : tasks) {
                writer.write(task.getFileOutput());
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new IOException("Failed to save to the file: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTaskList () throws IOException {
        ArrayList<String> taskStrings = readFile();
        return parseTaskList(taskStrings);
    }
}