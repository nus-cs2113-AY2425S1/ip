import exception.FlashException;
import java.util.Scanner;

/**
 * Main class for the Flash chatbot.
 * Handles interaction with the user and manages tasks through commands.
 */
public class Flash {

    private static final String FILE_PATH = "./data/flash.txt";
    private static Storage storage;
    private static TaskList taskList;

    /**
     * The entry point of the application.
     * Initializes storage and task list, loads tasks from the file,
     * and continuously listens for user commands.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        storage = new Storage(FILE_PATH);
        taskList = new TaskList();

        // Load tasks from file
        try {
            taskList.tasks = storage.load();
            UI.displayLoadSuccessMessage();
        } catch (FlashException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        }

        Scanner in = UI.readCommand();
        UI.displayWelcomeMessage();

        while (true) {
            try {
                String input = in.nextLine();
                String command = Parser.parseCommand(input);

                switch (command) {
                    case "bye":
                        UI.displayByeMessage();
                        return;
                    case "list":
                        UI.displayTasks(taskList.tasks);
                        break;
                    case "mark":
                        taskList.markTask(input);
                        storage.save(taskList.tasks);
                        break;
                    case "unmark":
                        taskList.unMarkTask(input);
                        storage.save(taskList.tasks);
                        break;
                    case "todo":
                        taskList.addTodo(input);
                        storage.save(taskList.tasks);
                        break;
                    case "deadline":
                        taskList.addDeadline(input);
                        storage.save(taskList.tasks);
                        break;
                    case "event":
                        taskList.addEvent(input);
                        storage.save(taskList.tasks);
                        break;
                    case "delete":
                        taskList.deleteTask(input);
                        storage.save(taskList.tasks);
                        break;
                    case "find":
                        taskList.listMatchedTasks(input);
                        break;
                    default:
                        throw new FlashException("Uh-oh! I don't know what that means.");
                }
            } catch (FlashException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }

}
