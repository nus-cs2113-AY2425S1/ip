import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {
    private Fenix fenix;
    private FileHandler fileHandler;

    public Storage(Fenix fenix) {
        this.fenix = fenix;
        fileHandler = new FileHandler();
    }

    public void loadAllInfo() {
        try {
            String fileContent = getFileInfo();
            if (!fileContent.isBlank()) {
                decipherAllInfo(fileContent);
            }
        } catch (FileNotFoundException ignored) {
        }
    }

    private String getFileInfo() throws FileNotFoundException {
        String fileContent = "";
        fileContent = fileHandler.loadFileContents();
        return fileContent;
    }

    private void decipherAllInfo(String fileContent) {
        String[] arrayOfTasks = fileContent.split("\n");
        for (String task : arrayOfTasks) {
            String[] stringArray = task.split("\\|");
            String taskType = stringArray[1];
            String taskStatus = stringArray[2];
            String taskInfo = stringArray[3].trim();
            fenix.addTaskFromStorage(taskType, taskStatus, taskInfo);
        }
    }

    public void writeAllTasks() {
        clearAllInfo();
        saveAllInfo();
    }

    private void clearAllInfo() {
        try {
            fileHandler.writeToFile("");
        } catch (IOException | NullPointerException ignored) {
        }
    }

    private void saveAllInfo() {
        for (Task task : fenix.getTaskArrayList()) {
            try {
                String taskToWrite = task.toString();
                taskToWrite = taskToWrite.replaceAll("\\[", "|");
                taskToWrite = taskToWrite.replaceAll("]", "|");
                taskToWrite = taskToWrite.replace("||", "|");
                fileHandler.appendToFile(taskToWrite);
                fileHandler.appendToFile("\n");
            } catch (IOException | NullPointerException e) {
                return;
            }
        }
    }
}
