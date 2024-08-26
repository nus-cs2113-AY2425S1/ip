import java.util.Scanner;

public class Luke {

    private static String horizontalLine = "____________________________________________________________";

    private static String[] storage = new String[100];
    private static boolean[] isDone = new boolean[100];
    private static int size = 0;

    private static void printReply(String reply) {
        System.out.println(horizontalLine);
        System.out.println(reply);
        System.out.println(horizontalLine);
    }

    private static void list() {
        System.out.println(horizontalLine);
        for (int i = 0; i < size; i++) {
            String status = (isDone[i] ? "X" : " ");
            System.out.print(String.format("%d.[%s] %s\n", i+1, status, storage[i]));
        }
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
         // Initialize array values
        for (int i = 0; i < 100; i++) {
            isDone[i] = false;
        }

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
            if (line.equalsIgnoreCase("list")) {
                list();
                continue;
            }
            String[] lineArr = line.split(" ");
            if (lineArr[0].equalsIgnoreCase("mark")) {
                int idx = Integer.parseInt(lineArr[1]) - 1;
                isDone[idx] = true;
                printReply(String.format("Marked as done:\n  [X] %s", storage[idx]));
            } else if (lineArr[0].equalsIgnoreCase("unmark")) {
                int idx = Integer.parseInt(lineArr[1]) - 1;
                isDone[idx] = false;
                printReply(String.format("Unmarked:\n  [ ] %s", storage[idx]));
            } else {
                printReply(String.format("Added task: %s", line));
                storage[size] = line;
                size++;
            }
        }
    }
}
