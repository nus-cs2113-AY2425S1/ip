import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileWriter;

public class DataManager {
    private File dataFile;

    public File getDataFile() {
        return dataFile;
    }

    public DataManager(String fileName) {
        dataFile = new File(fileName);
    }

    public void createFile() {
        try {
            if (dataFile.exists()) {
                return;                                     // return if data file already exists
            }
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();          // handle if parent file does not exist already
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create file: " + e.getMessage());
        }
    }

    private ArrayList readFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException();
        }
        if (dataFile.length() == 0) {
            return null;
        }

        return (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = null;
        createFile();
        try {
            ArrayList<String> dataItems = readFile();
            if (dataItems != null) {
                taskList = parse(dataItems);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(dataFile);
            for (Task task : Aerus.tasks) {
                fileWriter.write(task.toSaveString() + "\n");
            }
            fileWriter.close();
            System.out.println("Successfully saved data!");
        } catch (IOException e) {
            System.out.println("Cannot save data: " + e.getMessage());
        }
    }

    private ArrayList<Task> parse(ArrayList<String> dataItems) {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (String line : dataItems) {
            if (line != null && parseTask(line) != null) {
                allTasks.add(parseTask(line));
            }
        }
        return allTasks;
    }

    private Task parseTask(String line) {
        Task result = null;
        String taskType = getTaskType(line);
        switch (taskType) {
        case "T":
            String todoDescription = getTaskDescription(line);
            ToDo todo = new ToDo(todoDescription);
            result = todo;
            break;
        case "D":
            String deadlineDescription = getTaskDescription(line);
            String deadlineBy = getTaskFirstField(line);
            result = new Deadline(deadlineDescription, deadlineBy);
            break;
        case "E":
            String eventDescription = getTaskDescription(line);
            String eventFrom = getTaskFirstField(line);
            String eventTo = getTaskSecondField(line);
            result = new Event(eventDescription, eventFrom, eventTo);
            break;
        default:
            System.out.println("Unknown task encountered. Skipping");
            break;
        }

        boolean taskIsDone = getTaskIsDone(line);
        if (taskIsDone && result != null) {
            result.isDone = true;
        }
        return result;
    }

    private static String getTaskType(String inputLine) {
        return inputLine.substring(0,1);
    }

    private static boolean getTaskIsDone(String inputLine) {
        return inputLine.charAt(1) == 'X';
    }

    private static String getTaskDescription(String inputLine) {
        String[] taskSplit = inputLine.split("//");
        return taskSplit[1];
    }

    private static String getTaskFirstField(String inputLine) {
        String[] taskSplit = inputLine.split("//");
        if (taskSplit.length>=3) {
            return taskSplit[2];
        }
        return null;
    }

    private static String getTaskSecondField(String inputLine) {
        String[] taskSplit = inputLine.split("//");
        if (taskSplit.length>=4) {
            return taskSplit[3];
        }
        return null;
    }
}