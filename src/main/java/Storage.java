import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Storage {

    public static void loadSave(ArrayList<Task> listOfTasks, String saveFilePath) {
        if (Files.exists(Paths.get(saveFilePath))) {
            try {
                File saveFile = new File(saveFilePath);
                Scanner fileScanner = new Scanner(saveFile);
                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();
                    String[] lineArr = line.trim().split(" ");
                    String description;
                    switch (lineArr[0].toLowerCase()) {
                    case "mark":
                        int position = Integer.parseInt(lineArr[1]);
                        markTask(listOfTasks, position);
                        break;
                    case "todo":
                        description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                        addToDo(listOfTasks, description);
                        break;
                    case "deadline":
                        description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                        addDeadline(listOfTasks, description);
                        break;
                    case "event":
                        description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                        addEvent(listOfTasks, description);
                        break;
                    default:
                        System.out.println("An error occured while loading the save file");
                        break;
                    }
                }
                fileScanner.close();
                printBlock("Here are your list of tasks");
                displayList(listOfTasks);
            } catch (FileNotFoundException error) {
                System.out.println();
            } catch (NumberFormatException error) {
                printBlock("You need to input a valid integer for the task that you want to mark as done");
            } catch (InvalidDeadlineException error) {
                printBlock("You did not enter a valid deadline." + 
                        " Remember to add a \"/by\" before a valid deadline.");
            }
        } else {
            try {
                Files.createFile(Paths.get(saveFilePath));
            } catch (IOException error) {
                System.out.println(error);
            }
        }
    }

    public static void saveTasks(ArrayList<Task> listOfTasks, String saveFilePath) {
        try {
            File saveFile = new File(saveFilePath);
            FileWriter clearSaveFile = new FileWriter(saveFile);
            clearSaveFile.write("");
            clearSaveFile.close();
            FileWriter saveFileWriter = new FileWriter(saveFile, true);
            for (Task task: listOfTasks) {
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
                    saveFileWriter.write("mark " + (listOfTasks.indexOf(task) + 1) + System.lineSeparator());
                }
            }
            saveFileWriter.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }
}
