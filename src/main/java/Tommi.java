import java.util.Scanner;

public class Tommi {
    public static void main(String[] args) {
        String logo =
                  " ______                   \n"
                + "/_  __/__  __ _  __ _  (_)\n"
                + " / / / _ \\/  ' \\/  ' \\/ / \n"
                + "/_/  \\___/_/_/_/_/_/_/_/  \n";
        System.out.println(logo);
        String intro =
                  "____________________________________________________________\n"
                +  "Hello! I'm Tommi!\n"
                +  "What can I do for you?\n"
                +  "____________________________________________________________\n";
        System.out.println(intro);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
        }
    }
}
