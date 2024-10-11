import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static java.io.File f;
    private static String path;

    public static void init() throws IOException {
        f = new java.io.File(".data/SaveFile.txt");
        path = f.getAbsolutePath();
        if (!f.exists()) {
            if (f.getParentFile().mkdirs() && f.createNewFile()) {
                Ui.printSaveFileCreated();
            }
        }
        loadFile();
    }

    public static void clear() throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write("");
        fw.close();
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void loadFile() throws IOException {
        Scanner s = new Scanner(f);
        StringBuilder fileContent = new StringBuilder();
        while (s.hasNext()) {
            fileContent.append(s.nextLine());
            fileContent.append("\n");
        }
        String fileString = fileContent.toString();
        if (!fileString.isBlank()) {
            decipherAllInfo(fileString);
        }
    }

    public static void decipherAllInfo(String fileContent) {
        String[] arrayOfTasks = fileContent.split("\n");
        for (String task : arrayOfTasks) {
            String[] stringArray = task.split("\\|");
            String taskType = stringArray[0];
            String taskStatus = stringArray[1];
            String taskDesc = stringArray[2].trim();
            TaskList.addTaskFromSave(taskType, taskStatus, taskDesc);
        }
        Ui.printLoadConfirm();
    }

    public static void save() {
        for (int i = 0; i < TaskList.listCount; i += 1) {
            try {
                String taskString = TaskList.tasks[i].toString();
                String stringToWrite = taskString.substring(3);
                stringToWrite = stringToWrite.replaceAll("\\[", "|");
                stringToWrite = stringToWrite.replaceAll("]", "|");
                stringToWrite = stringToWrite.replaceAll("BY:", "/by");
                stringToWrite = stringToWrite.replaceAll("FROM:", "/from");
                stringToWrite = stringToWrite.replaceAll("TO:", "/to");
                stringToWrite = stringToWrite.replace("||", "|");
                appendToFile(stringToWrite);
                appendToFile("\n");
            } catch (IOException | NullPointerException e) {
                return;
            }
        }
    }

}
