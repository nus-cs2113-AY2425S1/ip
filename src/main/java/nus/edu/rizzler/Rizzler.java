package nus.edu.rizzler;

import java.util.Scanner;
import nus.edu.rizzler.exception.InvalidInputException;
import nus.edu.rizzler.manager.TaskManager;
import nus.edu.rizzler.ui.Emoji;

public class Rizzler {
    Emoji emoji = new Emoji();

    public void displayMenu() {
        System.out.println("What can I do to make your day pop? " + emoji.getPartyPopperEmoji() + emoji.getRocketEmoji());
        final String MENU = """
                  --------------------------------------------------------
                  1. To add a TODO task type 'todo [task]'
                     to add a DEADLINE task type 'deadline [task] BY [deadline]'
                     to add a EVENT task type 'event [task] FROM [start time] TO [end time]', and Rizzler's got it handled!\s
                  2. Need the full rundown? Type 'list' and I’ll drop the goods!
                  3. Crushed a task? Type 'mark [task number]' and let's celebrate!
                  4. Changed your mind? Type 'undo [task number]' and I'll reset that for you!
                  5. Wanna bounce? Just hit me with 'bye' and I'll catch you on the flip side!
                  --------------------------------------------------------
                 \s""";

        System.out.println(MENU);
    }

    public void displaySavedData() {
        TaskManager taskManager = new TaskManager();
        taskManager.load_data();
    }

    public void listenCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            try {
                handleCommand(command);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
            command = scanner.nextLine();
        }
        System.out.println("Peace out, Rizzler’s got places to be! " + emoji.getCoolFaceEmoji() + emoji.getFistBumpEmoji());
        scanner.close();
    }

    private void handleCommand(String command) throws InvalidInputException {
        TaskManager taskManager = new TaskManager();
        String[] commandParts = command.trim().split(" ", 2);
        String commandType = commandParts[0];

        switch (commandType) {
        case "list":
            taskManager.displayTaskList();
            break;

        case "mark":
            handleTaskCompletion(taskManager, commandParts);
            break;

        case "undo":
            handleTaskUndo(taskManager, commandParts);
            break;

        case "delete":
            handleTaskDeletion(taskManager, commandParts);
            break;

        case "todo":
        case "deadline":
        case "event":
            taskManager.addTask(command);
            break;

        default:
            throw new InvalidInputException("That's an invalid command! " + emoji.getCrossMarkEmoji() + emoji.getCrossMarkEmoji());
        }
    }

    private void handleTaskCompletion(TaskManager taskManager, String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("ERROR: Please follow the format --> 'mark [task number]'" + emoji.getExclamationMarkEmoji());
            return;
        }

        try {
            int taskNumber = Integer.parseInt(commandParts[1]);
            taskManager.completeTask(taskNumber);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Task number must be an integer --> 'mark [task number]'" + emoji.getExclamationMarkEmoji());
        }
    }

    private void handleTaskUndo(TaskManager taskManager, String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("ERROR: Please follow the format --> 'undo [task number]'" + emoji.getExclamationMarkEmoji());
            return;
        }

        try {
            int taskNumber = Integer.parseInt(commandParts[1]);
            taskManager.undoTask(taskNumber);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Task number must be an integer --> 'undo [task number]'" + emoji.getExclamationMarkEmoji());
        }
    }

    private void handleTaskDeletion(TaskManager taskManager, String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("ERROR: Please follow the format --> 'delete [task number]'" + emoji.getExclamationMarkEmoji());
            return;
        }

        try {
            int taskNumber = Integer.parseInt(commandParts[1]);
            taskManager.deleteTask(taskNumber);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Task number must be an integer --> 'delete [task number]'" + emoji.getExclamationMarkEmoji());
        }
    }

    public static void main(String[] args) {
        final String LOGO =
                """
                         _____                          _\s
                        |     \\                        | |\s
                        | |_)  |      ______   ______  | |   ____    _  __\s
                        |     /  (_) |__   /  |__   /  | | /      \\ | |/  \\\s
                        | |\\ \\   | |   /  /     /  /   | | |  ____/ |  _/\\_\\\s
                        | | \\ \\  | |  /  / __  /  / __ | | | |____  | | \s
                        |_|  \\_\\ |_| /______/ /______/ |_|  \\_____| |_|\s
                        """;

        Rizzler rizzler = new Rizzler();

        System.out.println(LOGO);
        System.out.println("Yo! I'm Rizzler.");
        rizzler.displayMenu();
        rizzler.displaySavedData();
        rizzler.listenCommand();
    }
}
