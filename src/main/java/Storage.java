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

public class Storage {
    private Ui ui = new Ui();
    private final String FILE_PATH = "./tasklist.txt";
    private ArrayList<Task> importedTaskListFromStorage = new ArrayList<>();
    private int taskCountFromStorage = 0;
    private TaskList importedTaskList;
    public Storage() {
        importTextFileData();
        importedTaskList = new TaskList(importedTaskListFromStorage, taskCountFromStorage);
    }

    public TaskList getloadedTaskList() {
        return importedTaskList;
    }

    public void addTextDataToArray(String[] words) throws LeginEmptyTaskException {
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

    public void importTextFileData() {
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

    public void updateTextFile(TaskList taskList) throws IOException {
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
