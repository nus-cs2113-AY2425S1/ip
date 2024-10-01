package blossom;

import java.util.Scanner;

public class Parser {
    private final String HORIZONTAL_LINE = "____________________________________________________________";
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Scanner scanner;

    Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            String command = scanner.nextLine();
            isRunning = executeCommand(command);
        }
        scanner.close();
        System.exit(0);
    }

    private boolean executeCommand(String command) {
        if (command.equalsIgnoreCase("bye")) {
            ui.printGoodbye();
            storage.saveTasks();
            return false; // Stop the program
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
                System.out.println(HORIZONTAL_LINE);
                System.out.println(e.getMessage());
                System.out.println(HORIZONTAL_LINE);
            }
        }
        return true; // Continue listening
    }
}
