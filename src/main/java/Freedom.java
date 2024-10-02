import java.util.ArrayList;
import java.util.Scanner;

import freedom.exceptions.InvalidCommand;
import freedom.tasks.TaskList;
import freedom.tasks.Task;
import freedom.user.Storage;

public class Freedom {
    static ArrayList<Task> tasks = new ArrayList<Task>();
    static int lastIndex = 0;

    private static TaskList taskList;
    private static Storage storage;

    static final String LOGO = "\t________________________________________\n";
    static final String DATA_FILE_PATH = "./data/freedom.txt";
    static final String DATA_DIRECTORY_PATH = "./data";

    public static void main(String[] args) {
        final String START_MESSAGE = """
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """;
        final String CLOSING_MESSAGE = "\tBye. Hope to see you again soon!\n";

        System.out.println(LOGO + START_MESSAGE + LOGO);

        storage = new Storage(DATA_FILE_PATH, DATA_DIRECTORY_PATH);

        Scanner in = new Scanner(System.in);

        try {
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
            System.out.print("");
        }

        System.out.println(LOGO + CLOSING_MESSAGE + LOGO);
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
            System.out.println(LOGO + "\tSorry! I do not understand your command");
            System.out.println(LOGO);
        } catch (Exception e) {
            System.out.print("");
        }
    }
}
