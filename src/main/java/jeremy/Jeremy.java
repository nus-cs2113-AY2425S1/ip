package jeremy;

import jeremy.command.Command;

import jeremy.exception.JeremyException;
import java.io.FileNotFoundException;

import jeremy.task.Deadline;
import jeremy.task.Event;
import jeremy.task.Todo;

import jeremy.util.Storage;
import jeremy.util.Ui;
import jeremy.util.TaskList;

import java.util.Scanner;

public class Jeremy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Jeremy() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.println("Storage file couldn't be created");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greeting();
        ui.logo();

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String[] parts = userInput.split(" ", 2);
            String commandStr = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            try {
                Command command = Command.fromString(commandStr);

                switch (command) {
                case LIST:
                    tasks.printList();
                    break;
                case MARK:
                    tasks.markTaskAsDone(argument);
                    break;
                case UNMARK:
                    tasks.markTaskAsNotDone(argument);
                    break;
                case DELETE:
                    tasks.deleteTask(argument);
                    break;
                case TODO:
                    tasks.addTask(new Todo(argument));
                    break;
                case DEADLINE:
                    tasks.addTask(new Deadline(argument));
                    break;
                case EVENT:
                    tasks.addTask(new Event(argument));
                    break;
                default:
                    // Below shouldn't ever run, since there's error
                    // handling for command type inside the enum
                    ui.println("How did you get here?");
                    break;
                }
            } catch (JeremyException e) {
                ui.lineBreak();
                ui.println(e.getMessage());
                ui.lineBreak();
            }

            userInput = scanner.nextLine();
        }

        storage.save(tasks);
        ui.bye();
    }

    public static void main(String[] args) {
        new Jeremy().run();
    }
}
