import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {
    FileHandler fileHandler;
    TaskHandler taskHandler;
    Parser parser;

    public Storage(FileHandler fileHandler, Parser parser, TaskHandler taskHandler) {
        this.fileHandler = fileHandler;
        this.taskHandler = taskHandler;
        this.parser = parser;
    }

    public void loadAllInfo() {
        try {
            String fileContent = getFileInfo();
            if (!fileContent.isBlank()) {
                decipherAllInfo(fileContent);
            }
        }
        catch (FileNotFoundException ignored) {
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
            taskHandler.storeTask(parser.returnTaskObject(taskType, taskStatus, taskInfo));
        }
    }

    public void writeAllTasks() {
        clearAllInfo();
        saveAllInfo();
    }

    private void clearAllInfo() {
        try {
            fileHandler.writeToFile("");
        }
        catch (IOException | NullPointerException ignored) {
        }
    }

    private void saveAllInfo() {
        for (Task task : taskHandler.getTaskArrayList()) {
            try {
                String taskToWrite = task.toString();
                taskToWrite = taskToWrite.replaceAll("\\[", "|");
                taskToWrite = taskToWrite.replaceAll("]", "|");
                taskToWrite = taskToWrite.replace("||", "|");
                fileHandler.appendToFile(taskToWrite);
                fileHandler.appendToFile("\n");
            }
            catch (IOException | NullPointerException e) {
                return;
            }
        }
    }
}
