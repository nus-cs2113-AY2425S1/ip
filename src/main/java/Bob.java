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

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}
