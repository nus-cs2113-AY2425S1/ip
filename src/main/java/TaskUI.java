import java.util.Scanner;

/**
 * Manages the user interface, including showing welcome messages, menus, and reading user input.
 */
public class TaskUI {

    private final Scanner in = new Scanner(System.in);

    /**
     * Reads a command input from the user.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Display the welcome message and menu to the user.
     */
    public void showWelcome() {
        String logo = "BBBB   RRRR   OOO  \n"
                + "B   B  R   R O   O \n"
                + "BBBB   RRRR  O   O \n"
                + "B   B  R  R  O   O \n"
                + "BBBB   R   R  OOO  \n";
        System.out.println("Hello from\n" + logo);
        menu();
    }


    /**
     * Display the available commands.
     */
    public void menu() {
        System.out.println("\nWelcome to the Task Manager! Here are your available commands:");
        System.out.println("1. list               - Show all tasks");
        System.out.println("2. todo <description> - Add a Todo task");
        System.out.println("3. deadline <description> /by <date> - Add a Deadline task");
        System.out.println("4. event <description> /from <start> /to <end> - Add an Event task");
        System.out.println("5. delete <task number> - Delete a task by its number");
        System.out.println("6. mark <task number> - Mark a task as done");
        System.out.println("7. unmark <task number> - Mark a task as not done");
        System.out.println("8. find <keyword> - Search tasks by keyword");
        System.out.println("9. Bye - Exit the program\n");
        System.out.println("Please enter a command:");
    }

}
