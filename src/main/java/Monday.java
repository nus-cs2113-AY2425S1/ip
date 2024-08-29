import java.util.Scanner;

public class Monday {
    public static void main(String[] args) {
        String logo = " __  __                 _             \n"
                + "|  \\/  | ___  _ __   __| | __ _ _   _ \n"
                + "| |\\/| |/ _ \\| '_ \\ / _` |/ _` | | | |\n"
                + "| |  | | (_) | | | | (_| | (_| | |_| |\n"
                + "|_|  |_|\\___/|_| |_|\\__,_|\\__,_|\\__, |\n"
                + "                                |___/  \n";
        String line = "____________________________________________________________";

        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println(line);

        System.out.println("Hello! I'm MONDAY\nWhat can I do for you?");

        System.out.println(line);

        while (true) {
            input = scanner.nextLine();
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
        }

    }
}
