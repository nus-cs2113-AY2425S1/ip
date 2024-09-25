import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents a storage that loads from a save file all the previous undeleted task from a 
 * previous run of <code>V</code> and saves all tasks after shutting down <code>V</code>.
 */
public class Storage {
    private ArrayList<String> commandArray = new ArrayList<String>();
    private String saveFilePath;

    /**
     * Clears all data from previous save file to allow overwriting of data.
     * @param saveFile File object of the save file
     */
    public void clearPreviousSave(File saveFile) {
        try {
            FileWriter clearSaveFile = new FileWriter(saveFile);
            clearSaveFile.write("");
            clearSaveFile.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    /**
     * Loads save file using file path saved in <code>saveFilePath</code> attribute.
     * Stores all the commands as a String in an array.
     */
    public void loadSave() {
        try {
            File saveFile = new File(this.saveFilePath);
            Scanner fileScanner = new Scanner(saveFile);
            while (fileScanner.hasNext()) {
                String command = fileScanner.nextLine();
                this.commandArray.add(command);
            }
            fileScanner.close();
        } catch (FileNotFoundException fileExceptionError) {
            try {
                Files.createFile(Paths.get(this.saveFilePath));
            } catch (IOException ioExceptionError) {
                System.out.println(ioExceptionError);
            }
        }
    }

    /**
     * Saves all tasks into a text file located in path
     * stated by attribute <code>saveFilePath</code>.
     * A new file is created if it a save file does not exist.
     * @param taskList list of tasks
     */
    public void createSave(TaskList taskList) {
        try {
            File saveFile = new File(this.saveFilePath);
            FileWriter saveFileWriter = new FileWriter(saveFile, true);

            clearPreviousSave(saveFile);
            
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

    /**
     * @return ArrayList of String containing all the commands from save file
     */
    public ArrayList<String> getCommandArray() {
        return this.commandArray;
    }

    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public Storage() {
    }
}
