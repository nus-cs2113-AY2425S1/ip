import java.util.ArrayList;
import java.util.Scanner;

public class KBot {

    private ArrayList<String> tasks;
    private Scanner scanner;

    public KBot() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        greetUser();

        while (true) {
            String input = getUserInput();

            switch (input) {
                case "bye":
                    exit();
                    return;

                case "list":
                    listTasks();
                    break;

                default:
                    addTask(input);
                    break;
            }
        }
    }

    private void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm KBot");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private String getUserInput() {
        return scanner.nextLine();
    }

    private void addTask(String task) {
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
    }

    private void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private void closeScanner() {
        scanner.close();
    }

    public static void main(String[] args) {
        KBot bot = new KBot();
        bot.run();
    }
}
