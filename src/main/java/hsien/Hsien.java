package hsien;

import hsien.exception.*;
import hsien.ui.*;
import hsien.storage.*;
import hsien.parser.*;
import hsien.tasklist.*;

import java.util.Arrays;
import java.util.List;



public class Hsien {

    public static void main(String[] args) {
        // Initialise variables
        Ui ui = new Ui();
        Storage storage = new Storage("tasks.txt");
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        List<String> validCommands = Arrays.asList("todo", "deadline", "event", "list", "mark", "unmark", "delete", "find","bye");
        boolean isRunning = true;

        ui.welcomeMessage();
        storage.readFile(taskList.getTasks());
        ui.printLine();

        while (isRunning) {
            ui.printCommands(validCommands);
            String input = ui.readCommand();

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
                    taskList.addTask(parser.getCommand(), parser.getDesc(), parser.getFromDate(), parser.getToDate(), parser.getByDate());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter in a proper format");
                }
                break;
            }
            ui.printLine();
        }
        ui.close();
        storage.writeFile(taskList.getTasks());
    }
}
