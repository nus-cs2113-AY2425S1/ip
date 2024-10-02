package freedom.user;

import freedom.exceptions.CannotCreateDirectory;
import freedom.exceptions.CannotReadFile;
import freedom.tasks.Task;
import freedom.tasks.TaskList;
import freedom.tasks.ToDo;
import freedom.tasks.Deadline;
import freedom.tasks.Event;
import freedom.ui.UiStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final UiStorage ui = new UiStorage();

    private String filePath;
    private String directoryPath;

    public Storage(String filePath, String directoryPath) {
        setFilePath(filePath);
        setDirectoryPath(directoryPath);
    }

    public ArrayList<Task> loadData() throws Exception{
        final int COMMAND_INDEX = 0;
        final int STATUS_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int TIME_ONE = 3;
        final int TIME_TWO = 4;

        boolean isDone;
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            checkData();

            File data = new File(getFilePath());
            Scanner read = new Scanner(data);
            while (read.hasNextLine()) {
                String[] words = read.nextLine().split("[|]");
                isDone = words[STATUS_INDEX].trim().equals("X");
                switch(words[COMMAND_INDEX].trim()) {
                case "T":
                    tasks.add(new ToDo(words[DESCRIPTION_INDEX].trim(), isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(words[DESCRIPTION_INDEX].trim(),
                            isDone, words[TIME_ONE].trim()));
                    break;
                case "E":
                    tasks.add(new Event(words[DESCRIPTION_INDEX].trim(), isDone,
                            words[TIME_ONE].trim(), words[TIME_TWO].trim()));
                    break;
                default:
                    throw new CannotReadFile();
                }
            }
            ui.printDataLoaded();
        } catch (FileNotFoundException e) {
            ui.printUnableToAccess();
        } catch (CannotReadFile e) {
            ui.printUnableToLoad();
            throw new Exception();
        } catch (Exception e) {
            ui.printPlaceholder();
        }

        return tasks;
    }

    public void saveData(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int lastIndex = taskList.getLastIndex();

        Task taskToAdd;

        try (FileWriter writer = new FileWriter(getFilePath())) {
            for (int i = 0; i < lastIndex; i++) {
                taskToAdd = tasks.get(i);
                writer.write(taskToAdd.generateStorageLine());
            }
        } catch (IOException e) {
            ui.printUnableToAccess();
        }
    }

    public void checkData() throws Exception{
        try {
            File directory = new File(getDirectoryPath());
            if(!directory.exists() && !directory.mkdir()) {
                throw new CannotCreateDirectory();
            }
            File dataFile = new File(getFilePath());
            if(dataFile.createNewFile()) {
                ui.printDataFileCreated();
            }
        } catch (IOException e) {
            ui.printUnableToCreateFile();
            throw new Exception();
        } catch (CannotCreateDirectory e) {
            ui.printUnableToCreateDirectory();
            throw new Exception();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}
