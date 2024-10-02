import java.util.Scanner;

import freedom.exceptions.InvalidCommand;
import freedom.tasks.TaskList;
import freedom.user.Storage;
import freedom.ui.Ui;

public class Freedom {
    private static TaskList taskList;
    private static Storage storage;
    private static Ui ui;

    static final String DATA_FILE_PATH = "./data/freedom.txt";
    static final String DATA_DIRECTORY_PATH = "./data";

    public static void main(String[] args) {

        ui = new Ui();
        storage = new Storage(DATA_FILE_PATH, DATA_DIRECTORY_PATH);

        Scanner in = new Scanner(System.in);

        try {
            ui.printStartMessage();
            taskList = new TaskList(storage.loadData());
            taskList.printList();

            // Super loop for getting inputs
            while (in.hasNextLine()) {
                String line = in.nextLine();

                if (line.equals("bye")) {
                    break;
                }
                handleInput(line);
            }

            storage.saveData(taskList);
        } catch (Exception e) {
            ui.printPlaceholder();
        }

        ui.printEndMessage();
    }

    public static void handleInput(String input) {
        final int COMMAND_INDEX = 0;
        String[] words = input.split(" ");

        try {
            switch (words[COMMAND_INDEX]) {
            case "list":
                taskList.printList();
                return;
            case "mark":
            case "unmark":
            case "delete":
                taskList.editTask(words);
                return;
            case "todo":
                taskList.addTask("todo", input);
                break;
            case "deadline":
                taskList.addTask("deadline", input);
                break;
            case "event":
                taskList.addTask("event", input);
                break;
            default:
                throw new InvalidCommand();

            }
            storage.saveData(taskList);
        } catch (InvalidCommand e) {
            ui.printInvalidCommand();
        } catch (Exception e) {
            ui.printPlaceholder();
        }
    }
}
