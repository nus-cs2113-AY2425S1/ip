package hsien;

import hsien.exception.HsienException;
import hsien.ui.Ui;
import hsien.storage.Storage;
import hsien.parser.Parser;
import hsien.tasklist.TaskList;

import java.util.Arrays;
import java.util.List;


/**
 * The Hsien class is the entry point for the application.
 * It initializes the user interface, storage, and task list,
 * and processes user commands in a loop until the user exits.
 *
 * This program act as a task planner and allows user to add,
 * delete and filter through tasks.
 */
public class Hsien {

    /**
     * Main method to start the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialise variables
        Ui ui = new Ui();
        Storage storage = new Storage("tasks.txt");
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        List<String> validCommands = Arrays.asList(
                "todo", "deadline", "event", "list", "mark",
                "unmark", "delete", "find", "save", "bye"
        );

        boolean isRunning = true;

        ui.welcomeMessage();
        storage.readFile(taskList.getTasks());
        ui.printLine();

        while (isRunning) {
            ui.printCommands(validCommands);
            String input = ui.readInput();

            if (!parser.processCommand(input)) {
                ui.printLine();
                continue;
            }

            try {
                if (!validCommands.contains(parser.getCommand())) {
                    throw new HsienException();
                }
            } catch (HsienException e) {
                System.out.println("Please enter a valid command from the list! ");
                ui.printLine();
                continue;
            }

            switch (parser.getCommand()) {
            case "bye":
                System.out.println("Have a good day! Bye!");
                isRunning = false;
                break;
            case "list":
                taskList.printList(taskList.getTasks());
                break;
            case "mark":
            case "unmark":
                try {
                    taskList.handleMark(parser.getCommand(), parser.getDesc());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format");
                }
                break;
            case "delete":
                try {
                    taskList.deleteTask(Integer.parseInt(parser.getDesc()) - 1);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format");
                }
                break;
            case "find":
                taskList.findTask(parser.getDesc());
                break;
            case "save":
                storage.writeFile(taskList.getTasks());
                break;
            default:
                try {
                    taskList.addTask(
                            parser.getCommand(), parser.getDesc(), parser.getFromDate(),
                            parser.getToDate(), parser.getByDate()
                    );
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter in a proper format: " + e.getMessage());
                }
                break;
            }
            ui.printLine();
        }
        // Tasks only gets saved when program exits and not through force stops
        storage.writeFile(taskList.getTasks());
        ui.close();
    }
}
