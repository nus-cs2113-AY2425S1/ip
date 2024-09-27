package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import task.*;
import ui.Ui;

import static main.Sirius.CREATE_DIRECTORY_MESSAGE;
import static main.Sirius.CREATE_FILE_MESSAGE;
import static main.Sirius.STATUS_DELIMINATOR;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTaskList() throws FileNotFoundException{
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(fromFileFormat(line));
            }
            scanner.close();
        } else {
            throw new FileNotFoundException();
        }
        return list;
    }

    public void saveTaskList(ArrayList<Task> list, Ui ui) {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                if (directory.mkdirs()){  // If the directory DNE, create.
                    ui.print(CREATE_DIRECTORY_MESSAGE);
                }
            }
            File file = new File(directory, "Sirius.txt");
            if (!file.exists()) {
                if (file.createNewFile()){  // If the file DNE, create and write.
                    ui.print(CREATE_FILE_MESSAGE);
                }
            }
            FileWriter writer = new FileWriter(file);  // override the previous contents in txt file.
            for (Task task : list) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    private Task fromFileFormat(String line) {
        String[] splitLine = line.split(STATUS_DELIMINATOR);
        String type = splitLine[0].trim();
        boolean isMarked = splitLine[1].trim().equals("1");
        String taskName = splitLine[2].trim();

        switch (type) {
            case "T":
                return new Todo(taskName, isMarked);
            case "D":
                return new Deadline(taskName, isMarked, splitLine[3].trim());
            case "E":
                return new Event(taskName, isMarked, splitLine[3].trim(), splitLine[4].trim());
            default:
                throw new IllegalArgumentException("Unknown task type");
        }
    }
}

