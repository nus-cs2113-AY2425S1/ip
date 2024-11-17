package storage;

import exception.LeginEmptyTaskException;
import task.TaskList;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import ui.Ui;
import java.io.File;
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
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int BY_INDEX = 3;
    private static final int FROM_INDEX = 3;
    private static final int TO_INDEX = 4;
    private static final String FILE_PATH = "./tasklist.txt";
    private final Ui ui = new Ui();
    private final ArrayList<Task> importedTaskListFromStorage = new ArrayList<>();
    private final TaskList importedTaskList;
    private int taskCountFromStorage = 0;

    /**
     * Retrieves data from storage text file and converts the data to an array of {@code Task} in
     * {@code importedTaskListFromStorage} <br>
     * Instantiates a new {@code TaskList} member with the imported data for the bot to retrieve
     */
    public Storage() {
        importTextFileData();
        importedTaskList = new TaskList(importedTaskListFromStorage, taskCountFromStorage);
    }

    private void addTextDataToArray(String[] words) throws LeginEmptyTaskException {
        String taskType = words[0];
        switch(taskType) {
        case "T":
            importedTaskListFromStorage.add(new Todo(words[TASK_DESCRIPTION_INDEX], true));
            break;
        case "D":
            importedTaskListFromStorage.add(new Deadline(words[TASK_DESCRIPTION_INDEX], words[BY_INDEX]));
            break;
        case "E":
            importedTaskListFromStorage.add(new Event(words[TASK_DESCRIPTION_INDEX], words[FROM_INDEX],
                    words[TO_INDEX]));
            break;
        }
        if (words[1].equals("true")) {
            importedTaskListFromStorage.get(taskCountFromStorage).markTask();
        }
        taskCountFromStorage++;
    }

    private void importTextFileData() {
        try {
            if (new File(FILE_PATH).createNewFile()) {
                return;
            }
            Scanner s = new Scanner(new File(FILE_PATH));
            while (s.hasNext()) {
                String[] wordsFromLineInTextFile = s.nextLine().split("\\|");
                addTextDataToArray(wordsFromLineInTextFile);
            }
        } catch (LeginEmptyTaskException | IOException e) {
            ui.printExceptionMessage(e);
        }
    }

    public TaskList getloadedTaskList() {
        return importedTaskList;
    }

    /**
     * Updates the storage text file with the current information when user terminates the bot
     *
     * @param taskList Contains information of all current task
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