package mong;

import mong.exception.IllegalTaskTypeException;
import mong.task.*;
import mong.ui.Parser;
import mong.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Mong {
    public static final String FILE_PATH = "./src/main/java/mong/data/mong.txt";
    public static Ui ui;
    public static TaskList taskList;

    /**
     * Writes new content to txt file.
     */
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves new content to file.
     */
    private static void saveToFile() {
        try {
            writeToFile(Parser.parseListToTxt());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Adds all tasks in txt file.
     */
    public static void loadFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Parser.parseLineContents(line);
        }
    }

    /**
     * Adds the command sent by the user into a list.
     * If the command "list" is sent, the list of previous commands will be printed.
     * If the command's first word is "mark", it will set the isCompleted variable of the Task to true.
     * If the command's first word is "unmark", it will set the isCompleted variable of the Task to false.
     * If the command "bye" is sent, the program will exit.
     */
    public static void addByTask() {
        String input = ui.getUserInput();
        TaskType command;
        try {
            command = Parser.decipherTaskType(input.split(" ")[0]);
        } catch (IllegalTaskTypeException e) {
            command = TaskType.INVALID;
        }
        while (command != TaskType.BYE) {
            ui.printHorizontalLine();
            switch(command) {
            case LIST:
                // print items in an indexed list
                TaskList.handleListCommand(input);
                break;
            case MARK:
                TaskList.mark(input);
                break;
            case UNMARK:
                TaskList.unmark(input);
                break;
            case DEADLINE:
                TaskList.addDeadline(input);
                break;
            case TODO:
                TaskList.addTodo(input);
                break;
            case EVENT:
                TaskList.addEvent(input);
                break;
            case DELETE:
                TaskList.deleteTask(input);
                break;
            default:
                System.out.println("MooONG?! That's not a valid command...");
                break;
            }
            saveToFile();
            ui.printHorizontalLine();
            input = ui.getUserInput();
            try {
                command = Parser.decipherTaskType(input.split(" ")[0]);
            } catch (IllegalTaskTypeException e) {
                command = TaskType.INVALID;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ui = new Ui();
        taskList = new TaskList();
        File directory = new File("./src/main/java/mong/data/");
        File file = new File(FILE_PATH);
        if (!directory.exists()) {
            directory.mkdir();
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        }
        try {
            loadFileContents(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        ui.showWelcomeMessage();
        addByTask();
        ui.showExitMessage();
    }
}
