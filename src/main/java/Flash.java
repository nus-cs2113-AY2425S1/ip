import exception.FlashException;

import java.util.Scanner;

public class Flash {

    private static final String FILE_PATH = "./data/flash.txt";
    private static Storage storage;
    private static TaskList taskList;

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

        while(true) {
            try {
                String input = in.nextLine();
                String command = Parser.parseCommand(input);

                if (command.equalsIgnoreCase("bye")) {
                    UI.displayByeMessage();
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    UI.displayTasks(taskList.tasks);
                } else if (command.equalsIgnoreCase("mark")) {
                    taskList.markTask(input);
                    storage.save(taskList.tasks);
                } else if (command.equalsIgnoreCase("unmark")) {
                    taskList.unMarkTask(input);
                    storage.save(taskList.tasks);
                } else if (command.equalsIgnoreCase("todo")) {
                    taskList.addTodo(input);
                    storage.save(taskList.tasks);
                } else if (command.equalsIgnoreCase("deadline")) {
                    taskList.addDeadline(input);
                    storage.save(taskList.tasks);
                } else if (command.equalsIgnoreCase("event")) {
                    taskList.addEvent(input);
                } else if (command.equalsIgnoreCase("delete")) {
                    taskList.deleteTask(input);
                    storage.save(taskList.tasks);
                } else {
                    throw new FlashException("Uh-oh! I don't know what that means.");
                }
            } catch (FlashException e){
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }

}
