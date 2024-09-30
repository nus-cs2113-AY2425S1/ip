import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        showLogo();
        showLine();
        System.out.println("Hello! I'm Bebe, your task manager.");
        System.out.println("What can I do for you today?");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("_______________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks.");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void showHelp() {
        System.out.println("Here are the commands you can use:");
        System.out.println("  todo <task description>          - Adds a ToDo task.");
        System.out.println("  deadline <task description> /by <date/time>  - Adds a Deadline task.");
        System.out.println("  event <task description> /from <start time> /to <end time>  - Adds an Event task.");
        System.out.println("  list                            - Lists all tasks.");
        System.out.println("  mark <task number>              - Marks a task as done.");
        System.out.println("  unmark <task number>            - Marks a task as not done.");
        System.out.println("  delete <task number>            - Deletes a task.");
        System.out.println("  location                        - Shows the location where the tasks are saved.");
        System.out.println("  help                            - Shows this help message.");
        System.out.println("  bye                             - Exits the chatbot.");
    }

    public void showLogo() {
        String logo = """
                 ____    ______   ____    ______ \s
                 |  _ \\  |  ____| |  _ \\  |  ____|\s
                 | |_) | | |__    | |_) | | |__   \s
                 |  _ <  |  __|   |  _ <  |  __|  \s
                 | |_) | | |____  | |_) | | |____ \s
                 |____/  |______| |____/  |______|\s
                """;
        System.out.println(logo);
    }
}
