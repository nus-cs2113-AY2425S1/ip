import java.util.Scanner;

public class JeM {

    public static void main(String[] args) {
        displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();

        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("bye")) {
                exitChatBot();
                break;
            }
            try {
                handleInput(line, storage);
            }catch(InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
        printBreakLine();
        scanner.close();
    }

    private static void displayWelcomeMessage() {
        String logo = "      _         __  __ \n"
                + "     | |       |  \\/  |\n"
                + "     | |  ___  | |\\/| |\n"
                + " _   | | / _ \\ | |  | |\n"
                + "| |__| ||  __/ | |  | |\n"
                + " \\____/  \\___| |_|  |_|\n";
        System.out.println("Hello from\n" + logo);

        printBreakLine();

        System.out.println(" Hello! I'm JeM, Your e-Assistant");
        System.out.println(" Personal To-Do list bot! ");
        System.out.println(" Just type out your tasks you have to complete and I will make a list of them for you.");
        System.out.println(" For tasks with no deadline, type todo <task name>, for tasks with deadlines, type deadline <task name> /by <date and time>");
        System.out.println(" For events, type event <task name> /from <date and time> /to <date and time>");
        System.out.println(" Type 'list' to see the current list of tasks, and type 'delete <task number>' to delete that task.");
        System.out.println(" Finally, type 'bye' to end the chat!");

        printBreakLine();
    }

    private static void handleInput(String line, Storage storage) throws InvalidCommandException {
        if (line.equalsIgnoreCase("list")) {
            storage.storageList();
        } else if (line.toLowerCase().startsWith("delete")) {
            handleDeleteCommand(line, storage);
        } else if (line.toLowerCase().startsWith("unmark")) {
            handleUnmarkCommand(line, storage);
        } else if (line.toLowerCase().startsWith("mark")) {
            handleMarkCommand(line, storage);
        } else if (line.toLowerCase().startsWith("todo")) {
            handleTodoCommand(line, storage);
        } else if (line.toLowerCase().startsWith("deadline")) {
            handleDeadlineCommand(line, storage);
        } else if (line.toLowerCase().startsWith("event")) {
            handleEventCommand(line, storage);
        } else {
            System.out.println("Unknown command: " + line);
        }
    }

    private static void handleDeleteCommand(String line, Storage storage) throws InvalidCommandException {
        int index = Integer.parseInt(line.split(" ")[1]);
        storage.storageDelete(index);
        storage.storageList();
    }

    private static void handleUnmarkCommand(String line, Storage storage) throws InvalidCommandException {
        int index = Integer.parseInt(line.split(" ")[1]);
        storage.storageUnmark(index);
        storage.storageList();
    }

    private static void handleMarkCommand(String line, Storage storage) throws InvalidCommandException {
        int index = Integer.parseInt(line.split(" ")[1]);
        storage.storageMark(index);
        storage.storageList();
    }

    private static void handleTodoCommand(String line, Storage storage) throws InvalidCommandException {
        String taskContent = line.substring(5).trim();
        Task task = new Todo(taskContent);
        storage.storageInsert(task);
    }

    private static void handleDeadlineCommand(String line, Storage storage) throws InvalidCommandException {
        String[] parts = line.substring(9).split(" /by ");
        String taskContent = parts[0].trim();
        String deadline = parts[1].trim();
        Task task = new Deadline(taskContent, deadline);
        storage.storageInsert(task);
    }

    private static void handleEventCommand(String line, Storage storage) throws InvalidCommandException {
        String[] parts = line.substring(6).split(" /from ");
        String taskContent = parts[0].trim();
        String[] dateTime = parts[1].split(" /to ");
        String start = dateTime[0].trim();
        String end = dateTime[1].trim();
        Task task = new Event(taskContent, start, end);
        storage.storageInsert(task);
    }

    private static void exitChatBot() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printBreakLine() {
        System.out.println("____________________________________________________________");
    }
}