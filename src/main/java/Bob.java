import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        String logo = "  ____        _     \n"
                + " | |_) \\ ___ | |___  \n"
                + " |  _ //  _  \\   _ \\ \n"
                + " | |_)\\\\ (_) /  |_) |\n"
                + " |____/ \\___/|_|___/ \n";
        System.out.println(logo);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input;
        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
