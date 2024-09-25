import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Storage {
    private ArrayList<String> commandArray = new ArrayList<String>();

    public void clearPreviousSave(File saveFile) {
        try {
            FileWriter clearSaveFile = new FileWriter(saveFile);
            clearSaveFile.write("");
            clearSaveFile.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    public void loadSave(String saveFilePath) {
        try {
            File saveFile = new File(saveFilePath);
            Scanner fileScanner = new Scanner(saveFile);
            while (fileScanner.hasNext()) {
                String command = fileScanner.nextLine();
                this.commandArray.add(command);
            }
            fileScanner.close();
        } catch (FileNotFoundException fileExceptionError) {
            try {
                Files.createFile(Paths.get(saveFilePath));
            } catch (IOException ioExceptionError) {
                System.out.println(ioExceptionError);
            }
        }
    }

    public void createSave(TaskList taskList, String saveFilePath) {
        try {
            File saveFile = new File(saveFilePath);

            clearPreviousSave(saveFile);

            FileWriter saveFileWriter = new FileWriter(saveFile, true);
            ArrayList<Task> list = taskList.getList();
            for (Task task: list) {
                switch(task.getType()) {
                case "T":
                    saveFileWriter.write("todo " + task.getDescription() + System.lineSeparator());
                    break;
                case "D":
                    saveFileWriter.write("deadline " + task.getDescription() + " /by " + task.getBy() + System.lineSeparator());
                    break;
                case "E":
                    saveFileWriter.write("event " + task.getDescription() + " /from " + task.getFrom() + " /to " + task.getTo() + System.lineSeparator());
                    break;
                default:
                    break;
                }
                if (task.getStatus().equals("X")) {
                    saveFileWriter.write("mark " + (list.indexOf(task) + 1) + System.lineSeparator());
                }
            }
            saveFileWriter.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    public ArrayList<String> getCommandArray() {
        return this.commandArray;
    }

    public Storage() {
    }
}
