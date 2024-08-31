import java.util.Scanner;

public class KBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm KBot");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        boolean working = true;


        while (working) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                working = false;
            }
        }

        scanner.close();
    }
}
