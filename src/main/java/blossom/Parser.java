package blossom;

import java.util.Scanner;

/**
 * The <code>Parser</code> class parses user input and handles command execution.
 * It reads and interprets commands from the user and executes corresponding actions
 * on the <code>TaskList</code>.
 */

public class Parser {
    private final String horizontalLine = "____________________________________________________________";
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Scanner scanner;

    /**
     * Constructs a Parser object that handles the logic for parsing and executing commands.
     *
     * @param ui the Ui to interact with the user
     * @param tasks the task list to manage tasks
     * @param storage the storage to manage saving and loading tasks
     */
    Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the command processing loop which continuously reads user input and processes commands
     * until the "bye" command is typed by the user.
     */
    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            String command = scanner.nextLine();
            isRunning = executeCommand(command);
        }
        scanner.close();
        // Terminates Blossom
        System.exit(0);
    }

    /**
     * Executes a given command by calling the suitable methods based on the command type.
     *
     * @param command the user input command to be executed
     * @return <code>false</code> if the command is "bye" (indicating the program should stop),
     *         <code>true</code> otherwise (indicating to continue processing commands).
     */
    public boolean executeCommand(String command) {
        if (command.equalsIgnoreCase("bye")) {
            ui.printGoodbye();
            storage.saveTasks();
            return false;
        } else if (command.equalsIgnoreCase("list")) {
            ui.printItems(tasks.getTasks());
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            // Call the unmark and mark function
            String[] parsedLine = command.split(" ");
            tasks.markAndUnmarkItem(Integer.parseInt(parsedLine[1]), parsedLine[0]);
        } else if (command.startsWith("delete")) {
            String[] parts = command.split(" ");
            tasks.deleteTask(Integer.parseInt(parts[1]));
        } else {
            try {
                tasks.addTask(command);
            } catch (BlossomException e) {
                System.out.println(horizontalLine);
                System.out.println(e.getMessage());
                System.out.println(horizontalLine);
            }
        }

        // Continue running Blossom
        return true;
    }
}
