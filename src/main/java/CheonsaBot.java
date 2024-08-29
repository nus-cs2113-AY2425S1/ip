import java.util.ArrayList;
import java.util.Scanner;

public class CheonsaBot {
    public static String horizontalLine = "____________________________________________________________";

    public static ArrayList<String> tasks = new ArrayList<String>();

    public static void main(String[] args) {
        String logo = """
                       (\\ -=- /)
                       ( \\( )/ )
                       (       )
                        `>   <'
                        /     \\
                        `-._.-'
                       """;
        System.out.println(horizontalLine);
        System.out.println("Hello, I'm 천사봇! (AngelBot)");
        System.out.print(logo);
        System.out.println("How may I assist you today?");
        System.out.println(horizontalLine);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();  // Read user input
            if (userInput.equalsIgnoreCase("bye")) {
                sayBye();
                break;
            }
            else if (userInput.equalsIgnoreCase("list")) {
                printTaskList();
            } else {
                addTask(userInput);
            }
        }
    }

    public static void sayBye() {
        System.out.println(horizontalLine);
        System.out.println("Bye, see you again soon!");
        System.out.println(horizontalLine);
    }

    public static void printTaskList() {
        System.out.println(horizontalLine);
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty... Try adding one!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(horizontalLine);
    }

    public static void addTask(String task) {
        tasks.add(task);
        System.out.println(horizontalLine);
        System.out.println("Added: " + task);
        System.out.println(horizontalLine);
    }
}
