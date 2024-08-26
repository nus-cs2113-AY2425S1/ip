import java.util.Scanner;

public class Luke {

    private static String horizontalLine = "____________________________________________________________";

    private static String[] storage = new String[100];
    private static int size = 0;

    private static void printReply(String reply) {
        System.out.println(horizontalLine);
        System.out.println(reply);
        System.out.println(horizontalLine);
    }

    private static void list() {
        System.out.println(horizontalLine);
        for (int i = 0; i < size; i++) {
            System.out.print(String.format("%d. %s\n", i+1, storage[i]));
        }
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        printReply("Hello! I'm Luke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String line;
        boolean exit = false;

        while (!exit) {
            line = in.nextLine();
            if (line.equalsIgnoreCase("Bye")) {
                printReply("Bye. Hope to see you again soon!");
                exit = true;
                continue;
            }
            if (line.equals("list")) {
                list();
            } else {
                printReply(String.format("Added task: %s", line));
                storage[size] = line;
                size++;
            }
        }
    }
}
