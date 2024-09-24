import exception.LeginEmptyTaskException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Imports and exports information between the bot and the storage text file. Imports the information from where the
 * last left off when Legin starts and exports the most updated information to the text file when the user terminates
 * the bot
 */
public class Storage {
    private Ui ui = new Ui();
    private final String FILE_PATH = "./tasklist.txt";
    private ArrayList<Task> importedTaskListFromStorage = new ArrayList<>();
    private int taskCountFromStorage = 0;
    private TaskList importedTaskList;

    /**
     * Retrieves data from storage text file and converts the data to an array of {@code Task} in
     * {@code importedTaskListFromStorage} <br>
     * Instantiates a new {@code TaskList} member with the imported data for the bot to retrieve
     */
    public Storage() {
        importTextFileData();
        importedTaskList = new TaskList(importedTaskListFromStorage, taskCountFromStorage);
    }

    public TaskList getloadedTaskList() {
        return importedTaskList;
    }

    /**
     * Adds the type of {@code Task} into the array of task {@code importedTaskListFromStorage}
     *
     * @param words Processed data
     * @throws LeginEmptyTaskException If the task has no description
     */
    private void addTextDataToArray(String[] words) throws LeginEmptyTaskException {
        String taskType = words[0];
        switch(taskType) {
        case "T":
            importedTaskListFromStorage.add(new Todo(words[2], true));
            break;
        case "D":
            importedTaskListFromStorage.add(new Deadline(words[2], words[3]));
            break;
        case "E":
            importedTaskListFromStorage.add(new Event(words[2], words[3], words[4]));
            break;
        }
        if (words[1].equals("true")) {
            importedTaskListFromStorage.get(taskCountFromStorage).markTask();
        }
        taskCountFromStorage++;
    }

    /**
     * Retrieves raw data from storage text file and calls {@code addTextDataToArray} with the processed data
     */
    private void importTextFileData() {
        try {
            if (!new File(FILE_PATH).exists()) {
                new File(FILE_PATH).createNewFile();
            }
            Scanner s = new Scanner(new File(FILE_PATH));
            while (s.hasNext()) {
                String[] wordsFromLineInTextFile = s.nextLine().split("\\|");
                addTextDataToArray(wordsFromLineInTextFile);
            }
        }catch (FileNotFoundException | LeginEmptyTaskException e) {
            ui.printExceptionMessage(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the storage text file with the current information when user terminates the bot
     *
     * @param taskList
     */
    public void updateTextFile(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int taskCount = taskList.getTaskCount();
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskCount; i++) {
                fw.write(tasks.get(i).getWriteInfo() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            ui.printExceptionMessage(e);
        }
    }
}
